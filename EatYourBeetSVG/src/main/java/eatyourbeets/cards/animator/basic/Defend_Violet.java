package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Defend_Violet extends ImprovedDefend
{
    public static final Affinity AFFINITY_TYPE = Affinity.Violet;
    public static final EYBCardData DATA = Register(Defend_Violet.class);

    public Defend_Violet()
    {
        super(DATA, AFFINITY_TYPE);
    }
}