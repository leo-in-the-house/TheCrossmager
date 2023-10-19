package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Defend_Brown extends ImprovedDefend
{
    public static final Affinity AFFINITY_TYPE = Affinity.Brown;
    public static final EYBCardData DATA = Register(Defend_Brown.class);

    public Defend_Brown()
    {
        super(DATA, AFFINITY_TYPE);
    }
}