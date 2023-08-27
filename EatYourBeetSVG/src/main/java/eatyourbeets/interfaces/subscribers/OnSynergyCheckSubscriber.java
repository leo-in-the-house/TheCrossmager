package eatyourbeets.interfaces.subscribers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;

public interface OnSynergyCheckSubscriber
{
    boolean OnSynergyCheck(AbstractCard a, AbstractCard b);
}