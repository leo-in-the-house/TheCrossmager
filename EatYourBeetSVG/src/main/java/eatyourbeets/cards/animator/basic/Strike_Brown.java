package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Strike_Brown extends ImprovedStrike
{
    public static final Affinity AFFINITY_TYPE = Affinity.Brown;
    public static final EYBCardData DATA = Register(Strike_Brown.class);

    public Strike_Brown()
    {
        super(DATA, AFFINITY_TYPE);
    }
}