package eatyourbeets.actions.damage;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import eatyourbeets.actions.EYBActionWithCallback;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.EYBEffect;
import eatyourbeets.interfaces.delegates.FuncT1;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.animator.StolenGoldPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class DealDamage extends EYBActionWithCallback<AbstractCreature>
{
    protected final DamageInfo info;

    protected FuncT1<Float, AbstractCreature> onDamageEffect;
    protected boolean hasPlayedEffect;
    protected boolean bypassBlock;
    protected boolean bypassThorns;
    protected boolean skipWait;
    protected boolean canKill;
    protected int goldAmount;

    protected Color vfxColor = null;
    protected Color enemyTint = null;
    protected Float vfxScale = null;
    protected float pitchMin = 0.95f;
    protected float pitchMax = 1.05f;

    public DealDamage(AbstractCreature target, DamageInfo info)
    {
        this(target, info, AttackEffect.NONE);
    }

    public DealDamage(AbstractCreature target, DamageInfo info, AttackEffect effect)
    {
        super(ActionType.DAMAGE, Settings.ACTION_DUR_XFAST);

        this.goldAmount = 0;
        this.skipWait = false;
        this.canKill = true;
        this.info = info;
        this.attackEffect = effect;

        Initialize(info.owner, target, info.output);

        CombatStats.OnCreateDamageAction(this, info);
    }

    public DealDamage SetDamageEffect(FuncT1<Float, AbstractCreature> onDamageEffect)
    {
        this.onDamageEffect = onDamageEffect;

        return this;
    }

    public DealDamage SetPiercing(boolean bypassThorns, boolean bypassBlock)
    {
        this.bypassBlock = bypassBlock;
        this.bypassThorns = bypassThorns;

        return this;
    }

    public DealDamage CanKill(boolean canKill)
    {
        this.canKill = canKill;

        return this;
    }

    public DealDamage SetVFXColor(Color color)
    {
        this.vfxColor = color.cpy();

        return this;
    }

    public DealDamage SetVFXColor(Color color, Color enemyTint)
    {
        this.vfxColor = color.cpy();
        this.enemyTint = enemyTint.cpy();

        return this;
    }

    public DealDamage SetVFXScale(Float scale)
    {
        this.vfxScale = scale;

        return this;
    }

    public DealDamage SetSoundPitch(float pitch)
    {
        return SetSoundPitch(pitch, pitch);
    }

    public DealDamage SetSoundPitch(float pitchMin, float pitchMax)
    {
        this.pitchMin = pitchMin;
        this.pitchMax = pitchMax;

        return this;
    }

    public DealDamage SetVFX(boolean superFast, boolean muteSfx)
    {
        this.skipWait = superFast;

        if (muteSfx)
        {
            this.pitchMin = this.pitchMax = 0;
        }

        return this;
    }

    public DealDamage StealGold(int goldAmount)
    {
        this.goldAmount = goldAmount;

        return this;
    }

    @Override
    protected boolean shouldCancelAction()
    {
        return super.shouldCancelAction() || (this.info.owner != null && (this.info.owner.isDying || this.info.owner.halfDead));
    }

    @Override
    protected void FirstUpdate()
    {
        if (this.info.type != DamageInfo.DamageType.THORNS && this.shouldCancelAction())
        {
            Complete();
            return;
        }

        if (onDamageEffect != null)
        {
            AddDuration(onDamageEffect.Invoke(target));
        }

        if (this.goldAmount > 0)
        {
            GameActions.Instant.StackPower(source, new StolenGoldPower(target, goldAmount));
        }
    }

    @Override
    protected void UpdateInternal(float deltaTime)
    {
        if (this.info.type != DamageInfo.DamageType.THORNS && shouldCancelAction())
        {
            Complete();
            return;
        }

        if (!hasPlayedEffect && duration <= 0.1f)
        {
            if (GameUtilities.IsValidTarget(target))
            {
                AddDuration(AttackEffects.GetDamageDelay(attackEffect));
                final EYBEffect effect = GameEffects.List.Attack(source, target, attackEffect, pitchMin, pitchMax, vfxColor);
                if (vfxScale != null)
                {
                    effect.SetScale(vfxScale);
                }
            }

            hasPlayedEffect = true;
        }

        if (TickDuration(deltaTime))
        {
            DamageHelper.ApplyTint(target, enemyTint, attackEffect);
            DamageHelper.DealDamage(target, info, bypassBlock, bypassThorns, canKill);

            if (GameUtilities.AreMonstersBasicallyDead())
            {
                GameUtilities.ClearPostCombatActions();
            }

            if (!this.skipWait && !Settings.FAST_MODE)
            {
                GameActions.Top.Wait(0.1f);
            }

            Complete(target);
        }
    }
}
