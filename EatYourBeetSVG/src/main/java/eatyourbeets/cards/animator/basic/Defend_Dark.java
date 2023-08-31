package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Defend_Dark extends ImprovedDefend
{
    public static final Affinity AFFINITY_TYPE = Affinity.Black;
    public static final EYBCardData DATA = Register(Defend_Dark.class);

    public Defend_Dark()
    {
        super(DATA, AFFINITY_TYPE);
    }
}