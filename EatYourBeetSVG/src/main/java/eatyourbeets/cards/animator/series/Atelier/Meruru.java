package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnCardCreatedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Meruru extends AnimatorCard {
    public static final EYBCardData DATA = Register(Meruru.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Meruru() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Yellow(1);
        SetAffinity_Blue(1);
        SetAffinity_Pink(1);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.MakeCardInDiscardPile(AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy());
        }

        GameActions.Bottom.StackPower(new MeruruPower(p, 1));
    }

    public static class MeruruPower extends AnimatorPower implements OnCardCreatedSubscriber {
        public MeruruPower(AbstractCreature owner, int amount) {
            super(owner, Meruru.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onCardCreated.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onCardCreated.Unsubscribe(this);
        }

        @Override
        public void OnCardCreated(AbstractCard card, boolean startOfBattle) {
            if (enabled && !startOfBattle) {
                if (card.costForTurn >= 0) {
                    GameUtilities.ModifyCostForCombat(card, 0, false);
                }
            }
        }
    }
}