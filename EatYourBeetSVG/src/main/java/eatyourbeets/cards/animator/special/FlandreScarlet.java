package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.TouhouProject.RemiliaScarlet;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.common.DelayedDamagePower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class FlandreScarlet extends AnimatorCard
{
    public static final EYBCardData DATA = Register(FlandreScarlet.class)
            .SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Piercing, EYBCardTarget.Random)
            .SetSeries(RemiliaScarlet.DATA.Series)
            ;

    public FlandreScarlet()
    {
        super(DATA);

        Initialize(8, 0, 2, 5);
        SetUpgrade(0, 0, 1);

        SetAffinity_Red(1, 0, 2);
        SetAffinity_Violet(1, 0, 2);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (startOfBattle && misc > 0)
        {
            GameActions.Bottom.ApplyVulnerable(player, player, misc);
            final AbstractCard card = GameUtilities.GetMasterDeckInstance(uuid);
            if (card != null)
            {
                card.misc = misc = 0;
            }
        }
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        if (CombatStats.TryActivateLimited(cardID))
        {
            GameActions.Bottom.MakeCardInHand(GameUtilities.Imitate(this)).Repeat(3);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.NONE)
                    .SetDamageEffect(enemy -> GameEffects.List.Add(VFX.Claw(enemy.hb, Color.SCARLET, Color.RED).SetScale(1.2f)).duration);
        }

        GameActions.Bottom.StackPower(new FlandreScarletPower(player, secondaryValue));
    }


    public static class FlandreScarletPower extends AnimatorPower implements OnBlockGainedSubscriber
    {
        public FlandreScarletPower(AbstractCreature owner, int amount)
        {
            super(owner, FlandreScarlet.DATA);

            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            CombatStats.onBlockGained.Subscribe(this);
        }

        @Override
        public void onRemove() {
            super.onRemove();

            CombatStats.onBlockGained.Unsubscribe(this);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            SetEnabled(false);
            RemovePower();
            flash();
        }

        @Override
        public void OnBlockGained(AbstractCreature creature, int block)
        {
            GameActions.Bottom.Callback(() -> {
                if (this.amount > 0) {
                    GameActions.Top.StackPower(player, new DelayedDamagePower(player, amount));
                    GameActions.Bottom.DealDamageToAll(DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AttackEffects.BITE)
                        .SetVFX(true, false)
                        .SetSoundPitch(1.3f, 1.4f)
                        .SetDuration(0.1f, true);
                }
            });
        }
    }
}