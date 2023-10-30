package eatyourbeets.powers.animator;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.RandomizedList;

public class ChlammyZellPower extends AnimatorPower
{
    public static final String POWER_ID = CreateFullID(ChlammyZellPower.class);

    private AbstractCard.CardType lastType;

    public ChlammyZellPower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        lastType = AbstractCard.CardType.SKILL;

        Initialize(amount);
    }

    @Override
    public void updateDescription()
    {
        this.description = FormatDescription(0, lastType.name());
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card)
    {
        super.onAfterCardPlayed(card);

        if (card.type != lastType)
        {
            lastType = card.type;

            RandomizedList<AbstractCard> zeroCosts = new RandomizedList<>();

            for (AbstractCard c : player.hand.group) {
                if (c.costForTurn == 0) {
                    zeroCosts.Add(c);
                }
            }

            if (zeroCosts.Size() > 0) {
                GameActions.Bottom.MakeCardInHand(zeroCosts.Retrieve(rng).makeCopy());
            }
            updateDescription();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        super.atEndOfTurn(isPlayer);

        RemovePower();
    }
}
