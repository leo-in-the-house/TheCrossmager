package eatyourbeets.interfaces.subscribers;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface OnAfterCardPurgedSubscriber
{
    void OnAfterCardPurged(AbstractCard card);
}