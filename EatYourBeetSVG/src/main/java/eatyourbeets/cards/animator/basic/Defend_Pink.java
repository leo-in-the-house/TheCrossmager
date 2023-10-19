package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Defend_Pink extends ImprovedDefend
{
    public static final Affinity AFFINITY_TYPE = Affinity.Pink;
    public static final EYBCardData DATA = Register(Defend_Pink.class);

    public Defend_Pink()
    {
        super(DATA, AFFINITY_TYPE);
    }
}