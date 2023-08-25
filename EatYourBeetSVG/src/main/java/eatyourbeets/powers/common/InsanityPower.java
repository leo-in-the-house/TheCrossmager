package eatyourbeets.powers.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.powers.CommonPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class InsanityPower extends CommonPower
{
    public static final String POWER_ID = CreateFullID(InsanityPower.class);

    //All Commons, Uncommons, Rares, and Colorless cards
    protected final static RandomizedList<AbstractCard> allCards = new RandomizedList<>();

    public InsanityPower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        Initialize(amount);
    }

    @Override
    public void updateDescription()
    {
        this.description = FormatDescription(0, 3 * amount);
    }

    @Override
    public void atStartOfTurn()
    {
        super.atStartOfTurn();

        if (allCards.Size() == 0) {
            InitializePossibleCards();
        }

        if (amount > 0)
        {
            int numCards = 3 * amount;

            for (int i=0; i<numCards; i++) {
                GameActions.Bottom.MakeCardInDrawPile(allCards.Retrieve(rng).makeCopy())
                        .SetUpgrade(true, false)
                        .AddCallback(card -> {
                            if (card.costForTurn > 0)
                            {
                                final String key = card.cardID + card.uuid;

                                CostModifiers.For(card).Add(key, -1);

                                GameUtilities.TriggerWhenPlayed(card, key, (k, c) ->
                                {
                                    CostModifiers.For(c).Remove(k, false);
                                });
                            }
                        });
            }

            flashWithoutSound();
        }
    }

    private void InitializePossibleCards()
    {
        for (AbstractCard c : CardLibrary.getAllCards())
        {
            if (c instanceof AnimatorCard && !GameUtilities.IsHindrance(c)
                    && !c.hasTag(AbstractCard.CardTags.HEALING)
                    && c.rarity != AbstractCard.CardRarity.SPECIAL
                    && c.rarity != AbstractCard.CardRarity.BASIC)
            {
                allCards.Add(c);
            }
        }
    }
}