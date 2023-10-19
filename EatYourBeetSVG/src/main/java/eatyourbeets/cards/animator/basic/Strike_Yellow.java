package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Strike_Yellow extends ImprovedStrike
{
    public static final Affinity AFFINITY_TYPE = Affinity.Yellow;
    public static final EYBCardData DATA = Register(Strike_Yellow.class);

    public Strike_Yellow()
    {
        super(DATA, AFFINITY_TYPE);
    }
}