package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnAfterCardDiscardedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ChaikaKamaz extends AnimatorCard {
    public static final EYBCardData DATA = Register(ChaikaKamaz.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.HitsugiNoChaika);

    public ChaikaKamaz() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Black(1);
        SetAffinity_Violet(1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new ChaikaKamazPower(p, 1, upgraded));
    }

    public static class ChaikaKamazPower extends AnimatorPower implements OnAfterCardDiscardedSubscriber {

        private boolean upgraded = false;
        public ChaikaKamazPower(AbstractCreature owner, int amount, boolean upgraded) {
            super(owner, ChaikaKamaz.DATA);

            this.upgraded = upgraded;

            Initialize(amount);
        }


        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAfterCardDiscarded.Subscribe(this);
        }


        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAfterCardDiscarded.Unsubscribe(this);
        }


        @Override
        public void stackPower(int stackAmount)
        {
            super.stackPower(stackAmount);
            updateDescription();
        }

        @Override
        public void OnAfterCardDiscarded() {
            GameActions.Bottom.MakeCardInHand( AbstractDungeon.getCard(CardRarity.COMMON).makeCopy())
                    .SetUpgrade(upgraded, true);
        }

        @Override
        public void updateDescription() {
            if (upgraded) {
                description = FormatDescription(1, amount);
            }
            else {
                description = FormatDescription(0, amount);
            }
        }
    }
}