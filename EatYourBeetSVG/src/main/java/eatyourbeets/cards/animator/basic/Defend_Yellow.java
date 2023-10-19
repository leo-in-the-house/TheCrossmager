package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Defend_Yellow extends ImprovedDefend
{
    public static final Affinity AFFINITY_TYPE = Affinity.Yellow;
    public static final EYBCardData DATA = Register(Defend_Yellow.class);

    public Defend_Yellow()
    {
        super(DATA, AFFINITY_TYPE);
    }
}