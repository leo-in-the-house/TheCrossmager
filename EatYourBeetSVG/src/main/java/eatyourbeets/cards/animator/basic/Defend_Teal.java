package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Defend_Teal extends ImprovedDefend
{
    public static final Affinity AFFINITY_TYPE = Affinity.Teal;
    public static final EYBCardData DATA = Register(Defend_Teal.class);

    public Defend_Teal()
    {
        super(DATA, AFFINITY_TYPE);
    }
}