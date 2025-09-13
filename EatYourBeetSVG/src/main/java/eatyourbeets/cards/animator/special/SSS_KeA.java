package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnCardRetainedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SSS_KeA extends AnimatorCard {
    public static final EYBCardData DATA = Register(SSS_KeA.class)
            .SetPower(1, CardRarity.SPECIAL)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public SSS_KeA() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(1);
        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new SSS_KeAPower(p, 1));
    }

    public static class SSS_KeAPower extends AnimatorPower implements OnCardRetainedSubscriber {
        public SSS_KeAPower(AbstractCreature owner, int amount) {
            super(owner, SSS_KeA.DATA);
            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onCardRetained.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onCardRetained.Unsubscribe(this);
        }

        @Override
        public void OnCardRetained(AbstractCard card) {
            GameActions.Bottom.MakeCardInDrawPile(card.makeStatEquivalentCopy())
                    .Repeat(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}