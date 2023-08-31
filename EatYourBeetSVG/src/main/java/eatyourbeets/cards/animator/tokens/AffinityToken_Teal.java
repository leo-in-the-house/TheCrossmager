package eatyourbeets.cards.animator.tokens;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class AffinityToken_Teal extends AffinityToken
{
    public static final EYBCardData DATA = RegisterAffinityToken(AffinityToken_Teal.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Teal;

    public AffinityToken_Teal()
    {
        super(DATA, AFFINITY_TYPE);
    }
}