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
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

import java.util.ArrayList;

public class NinaCliffe extends AnimatorCard
{
    public static final EYBCardData DATA = Register(NinaCliffe.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();
    public NinaCliffe()
    {
        super(DATA);

        Initialize(0, 0, 5, 0);

        SetAffinity_Blue(1);
        SetAffinity_Pink(1);
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.PurgeFromPile(name, magicNumber, p.hand, p.discardPile)
        .SetOptions(false, false)
        .SetFilter(GameUtilities::IsLowCost)
        .AddCallback(cards ->
        {
            if (cards.size() > 0)
            {
                GameActions.Bottom.StackPower(new NinaCliffePower(player, cards));
            }
        });
    }

    public static class NinaCliffePower extends AnimatorPower
    {
        private final RandomizedList<AbstractCard> cards;

        public NinaCliffePower(AbstractCreature owner, ArrayList<AbstractCard> cards)
        {
            super(owner, NinaCliffe.DATA);

            this.cards = new RandomizedList<>();

            Initialize(this.cards.Size());
        }

        @Override
        public void updateDescription()
        {
            if (cards.Size() > 0)
            {
                this.description = FormatDescription(0, amount);
            }
            else
            {
                this.description = "";
            }
        }

        @Override
        protected void OnSamePowerApplied(AbstractPower power)
        {
            super.OnSamePowerApplied(power);

            final NinaCliffePower other = (NinaCliffePower) power;
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

            for (int i = 0; i < cards.Size(); i++)
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
            }

            for (int i=0; i<2; i++) {
                final AbstractCard toPlay = cards.Retrieve(rng).makeCopy();
                toPlay.target_x = MoveCard.DEFAULT_CARD_X_LEFT;
                toPlay.target_y = MoveCard.DEFAULT_CARD_Y;
                GameActions.Last.PlayCard(toPlay, CombatStats.PurgedCards, null);
                flash();
            }
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