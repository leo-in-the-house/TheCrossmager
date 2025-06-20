package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class NonomiIzayoi extends AnimatorCard {
    public static final EYBCardData DATA = Register(NonomiIzayoi.class)
            .SetSkill(3, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public NonomiIzayoi() {
        super(DATA);

        Initialize(0, 0, 10);
        SetUpgrade(0, 12, 0);

        SetAffinity_Yellow(2);
        SetAffinity_Pink(1);

        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        if (!upgraded)
        {
            return null;
        }

        return super.GetBlockInfo();
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        if (upgraded) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.ChangeStance(new TranceStance());
        GameActions.Bottom.StackPower(new NonomiIzayoiPower(p, magicNumber));
    }

    public static class NonomiIzayoiPower extends AnimatorPower implements OnStanceChangedSubscriber
    {
        public NonomiIzayoiPower(AbstractPlayer owner, int amount)
        {
            super(owner, NonomiIzayoi.DATA);

            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onStanceChanged.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onStanceChanged.Unsubscribe(this);
        }

        @Override
        public void onUseCard(AbstractCard card, UseCardAction action)
        {
            super.onUseCard(card, action);

            if (card.type == CardType.ATTACK)
            {
                for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                    if (enemy.hasPower(LockOnPower.POWER_ID)) {
                        GameActions.Bottom.VFX(VFX.ThrowDagger(enemy.hb, 0.2f));
                        GameActions.Bottom.DealDamage(owner, enemy, amount, DamageInfo.DamageType.THORNS, AttackEffects.NONE)
                                .SetVFX(true, true);
                    }
                }
            }
        }

        @Override
        public void OnStanceChanged(AbstractStance oldStance, AbstractStance newStance) {
            RemovePower();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}