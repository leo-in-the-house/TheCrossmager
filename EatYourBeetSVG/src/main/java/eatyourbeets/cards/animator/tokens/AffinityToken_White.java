package eatyourbeets.cards.animator.tokens;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class AffinityToken_White extends AffinityToken
{
    public static final EYBCardData DATA = RegisterAffinityToken(AffinityToken_White.class);
    public static final Affinity AFFINITY_TYPE = Affinity.White;

    public AffinityToken_White()
    {
        super(DATA, AFFINITY_TYPE);
    }
}