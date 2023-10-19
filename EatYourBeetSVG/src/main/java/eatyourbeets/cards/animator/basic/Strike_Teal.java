package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Strike_Teal extends ImprovedStrike
{
    public static final Affinity AFFINITY_TYPE = Affinity.Teal;
    public static final EYBCardData DATA = Register(Strike_Teal.class);

    public Strike_Teal()
    {
        super(DATA, AFFINITY_TYPE);
    }
}