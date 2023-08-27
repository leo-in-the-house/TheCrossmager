package eatyourbeets.interfaces.subscribers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;

public interface OnSynergySubscriber
{
    void OnSynergy(AbstractCard card);
}