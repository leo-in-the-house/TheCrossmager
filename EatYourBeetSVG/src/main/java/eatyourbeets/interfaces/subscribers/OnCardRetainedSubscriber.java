package eatyourbeets.interfaces.subscribers;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface OnCardRetainedSubscriber {

    void OnCardRetained(AbstractCard card);
}
