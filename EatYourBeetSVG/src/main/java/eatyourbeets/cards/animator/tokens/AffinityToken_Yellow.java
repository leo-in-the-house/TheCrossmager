package eatyourbeets.cards.animator.tokens;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class AffinityToken_Yellow extends AffinityToken
{
    public static final EYBCardData DATA = RegisterAffinityToken(AffinityToken_Yellow.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Yellow;

    public AffinityToken_Yellow()
    {
        super(DATA, AFFINITY_TYPE);
    }
}