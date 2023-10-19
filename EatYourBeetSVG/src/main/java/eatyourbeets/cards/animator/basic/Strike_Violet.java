package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class Strike_Violet extends ImprovedStrike
{
    public static final Affinity AFFINITY_TYPE = Affinity.Violet;
    public static final EYBCardData DATA = Register(Strike_Violet.class);

    public Strike_Violet()
    {
        super(DATA, AFFINITY_TYPE);
    }
}