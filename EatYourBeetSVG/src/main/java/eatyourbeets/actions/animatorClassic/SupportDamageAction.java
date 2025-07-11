package eatyourbeets.actions.animatorClassic;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import eatyourbeets.actions.EYBAction;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class SupportDamageAction extends EYBAction
{
    private final DamageInfo info;

    public static AbstractMonster FindBestTarget()
    {
        return JUtils.FindMin(GameUtilities.GetEnemies(true), m -> m.currentHealth);
    }

    public SupportDamageAction(DamageInfo info)
    {
        this(info, FindBestTarget());
    }

    public SupportDamageAction(DamageInfo info, AbstractCreature target)
    {
        super(ActionType.DAMAGE);

        this.info = info;
        this.attackEffect = AttackEffect.NONE;

        Initialize(info.owner, target, info.output);
    }

    @Override
    protected boolean shouldCancelAction()
    {
        return target == null || (source != null && source.isDying);
    }

    @Override
    protected void FirstUpdate()
    {
        if (shouldCancelAction())
        {
            Complete();
            return;
        }

        AbstractCreature bestTarget = FindBestTarget();

        if (target != bestTarget || target.isDeadOrEscaped() || target.currentHealth <= 0)
        {
            if (GameUtilities.GetEnemies(true).size() > 0)
            {
                GameActions.Top.Add(new SupportDamageAction(info, bestTarget));
            }

            Complete();
            return;
        }

        GameEffects.List.Add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect));
    }

    @Override
    protected void UpdateInternal(float deltaTime)
    {
        if (shouldCancelAction())
        {
            Complete();
            return;
        }

        if (TickDuration(deltaTime))
        {
            if (attackEffect == AttackEffect.POISON)
            {
                target.tint.color = Color.CHARTREUSE.cpy();
                target.tint.changeColor(Color.WHITE.cpy());
            }
            else if (attackEffect == AttackEffect.FIRE)
            {
                target.tint.color = Color.RED.cpy();
                target.tint.changeColor(Color.WHITE.cpy());
            }

            info.type = DamageInfo.DamageType.NORMAL;
            info.applyPowers(info.owner, target);
            GameUtilities.RemoveDamagePowers();

            if (info.output < 10)
            {
                GameEffects.List.Add(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()));
            }
            else
            {
                GameEffects.List.Add(new DieDieDieEffect());
            }

            info.owner = null;
            target.damage(info);

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
            {
                GameUtilities.ClearPostCombatActions();
            }
        }
    }
}
