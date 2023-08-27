package eatyourbeets.interfaces.listeners;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;

public interface OnAddToDeckListener
{
    default boolean OnAddToDeck()
    {
        return true;
    }

    default boolean OnAddToDeck(AbstractCard card)
    {
        return OnAddToDeck();
    }
}