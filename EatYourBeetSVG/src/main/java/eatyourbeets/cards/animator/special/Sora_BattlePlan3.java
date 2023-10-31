package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Sora_BattlePlan3 extends Sora_BattlePlan
{
    public static final EYBCardData DATA = Register(Sora_BattlePlan3.class)
            .SetPower(0, CardRarity.SPECIAL)
            .SetImagePath(IMAGE_PATH)
            .SetSeries(SERIES);

    public Sora_BattlePlan3()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Blue(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new SoraBattlePlanPower(p, magicNumber));
    }

    public static class SoraBattlePlanPower extends AnimatorClickablePower {
        private int damage;

        public SoraBattlePlanPower(AbstractCreature owner, int amount) {
            super(owner, Sora_BattlePlan3.DATA, PowerTriggerConditionType.None, 0);

            this.triggerCondition.SetUses(amount, false, true);

            this.damage = damage;
            Initialize(amount, PowerType.BUFF, true);
        }

        @Override
        public String GetUpdatedDescription() {
            return FormatDescription(0, damage);
        }

        @Override
        protected void OnSamePowerApplied(AbstractPower power)
        {
            super.OnSamePowerApplied(power);
        }

        @Override
        public AbstractPower makeCopy()
        {
            return new SoraBattlePlanPower(owner, amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            playApplyPowerSfx();

            GameActions.Bottom.SelectFromHand(name, 1, false)
                .SetFilter(card -> card.costForTurn == 0)
                .SetMessage(GR.Common.Strings.HandSelection.Activate)
                .SetOptions(false, false, false)
                .AddCallback(cards -> {
                    for (AbstractCard card : cards) {
                        for (int i=0; i<2; i++) {
                            GameActions.Bottom.PlayCopy(card, GameUtilities.GetRandomEnemy(true));
                        }
                    }
                });
        }
    }
}