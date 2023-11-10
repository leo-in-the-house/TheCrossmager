package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AkaneSenri extends AnimatorCard {
    public static final EYBCardData DATA = Register(AkaneSenri.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public AkaneSenri() {
        super(DATA);

        Initialize(0, 6, 0);
        SetUpgrade(0, 0, 0);

        SetHaste(true);

        SetAffinity_Blue(2, 0, 2);
        SetAffinity_Black(2, 0, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChangeStance(MagicStance.STANCE_ID);
        GameActions.Bottom.StackPower(new AkaneSenriPower(p, 1, upgraded));
    }

    public static class AkaneSenriPower extends AnimatorClickablePower {

        private boolean upgrade;

        public AkaneSenriPower(AbstractCreature owner, int amount, boolean upgraded) {
            super(owner, AkaneSenri.DATA, PowerTriggerConditionType.Energy, 1);

            triggerCondition.SetUses(1, true, true);
            canBeZero = true;

            this.upgrade = upgraded;

            Initialize(amount);
        }

        @Override
        public void stackPower(int stackAmount)
        {
            super.stackPower(stackAmount);
            updateDescription();
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(upgrade ? 1 : 0, amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            for (int i=0; i<4; i++) {
                if (player.drawPile.size() > i){
                    AbstractCard card = player.drawPile.getNCardFromTop(i);

                    if (card != null) {
                        if (card instanceof EYBCard) {
                            ((EYBCard) card).SetHaste(true);
                        }

                        if (upgrade) {
                            GameActions.Bottom.Motivate(card, 1);
                        }
                    }
                }
                else {
                    break;
                }
            }
        }
    }
}