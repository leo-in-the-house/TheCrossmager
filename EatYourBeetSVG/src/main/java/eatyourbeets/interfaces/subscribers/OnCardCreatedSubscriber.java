package eatyourbeets.interfaces.subscribers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;

public interface OnCardCreatedSubscriber
{
    void OnCardCreated(AbstractCard card, boolean startOfBattle);
}
