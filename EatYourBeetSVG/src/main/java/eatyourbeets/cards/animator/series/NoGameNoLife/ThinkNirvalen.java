package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.actions.basic.MoveCard;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

import java.util.ArrayList;

public class ThinkNirvalen extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ThinkNirvalen.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();
    public ThinkNirvalen()
    {
        super(DATA);

        Initialize(0, 0, 3, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(1);
        SetAffinity_Pink(1);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        if (super.cardPlayable(m)) {
            int numZeroCostCards = 0;

            for (AbstractCard card : player.hand.group) {
                if (!card.uuid.equals(uuid) && (card.type == CardType.ATTACK || card.type == CardType.SKILL) && card.costForTurn == 0) {
                    numZeroCostCards++;
                }
            }

            for (AbstractCard card : player.discardPile.group) {
                if (!card.uuid.equals(uuid) && (card.type == CardType.ATTACK || card.type == CardType.SKILL) && card.costForTurn == 0) {
                    numZeroCostCards++;
                }
            }

            return numZeroCostCards >= 3;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.PurgeFromPile(name, magicNumber, p.hand, p.discardPile)
        .SetOptions(false, false)
        .SetFilter(card -> card.costForTurn == 0 && (card.type == CardType.ATTACK || card.type == CardType.SKILL))
        .AddCallback(cards ->
        {
            if (cards.size() > 0)
            {
                GameActions.Bottom.StackPower(new ThinkNirvalenPower(player, cards));
            }
        });
    }

    public static class ThinkNirvalenPower extends AnimatorPower
    {
        private final RandomizedList<AbstractCard> cards;

        public ThinkNirvalenPower(AbstractCreature owner, ArrayList<AbstractCard> cards)
        {
            super(owner, ThinkNirvalen.DATA);

            this.cards = new RandomizedList<>();
            this.cards.AddAll(cards);

            Initialize(this.cards.Size());
        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(0, amount);
        }

        @Override
        protected void OnSamePowerApplied(AbstractPower power)
        {
            super.OnSamePowerApplied(power);

            final ThinkNirvalenPower other = (ThinkNirvalenPower) power;
            if (other != null)
            {
                this.cards.AddAll(other.cards.GetInnerList());
                RefreshAmount();
            }
        }

        @Override
        public void atStartOfTurnPostDraw()
        {
            super.atStartOfTurnPostDraw();

           /* for (int i = 0; i < cards.Size(); i++)
            {
                final AbstractCard c = cards.GetInnerList().get(i);
                if (!CombatStats.PurgedCards.contains(c))
                {
                    cards.Remove(c);

                    if (!RefreshAmount())
                    {
                        return;
                    }
                }
            }*/


            final AbstractCard toPlay = cards.Retrieve(rng, false).makeCopy();
            toPlay.target_x = MoveCard.DEFAULT_CARD_X_LEFT;
            toPlay.target_y = MoveCard.DEFAULT_CARD_Y;
            GameActions.Top.PlayCopy(toPlay, GameUtilities.GetRandomEnemy(true))
                    .SetPurge(true);
            flash();
        }

        private boolean RefreshAmount()
        {
            if (amount > cards.Size())
            {
                reducePower(amount - cards.Size());
            }
            else if (amount > cards.Size())
            {
                stackPower(cards.Size() - amount);
            }

            if (amount <= 0)
            {
                RemovePower();
                return false;
            }

            return true;
        }
    }
}