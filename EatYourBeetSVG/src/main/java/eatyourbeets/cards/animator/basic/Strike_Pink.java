package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Strike_Pink extends ImprovedStrike
{
    public static final Affinity AFFINITY_TYPE = Affinity.Pink;
    public static final EYBCardData DATA = Register(Strike_Pink.class);

    public Strike_Pink()
    {
        super(DATA, AFFINITY_TYPE);
    }
}