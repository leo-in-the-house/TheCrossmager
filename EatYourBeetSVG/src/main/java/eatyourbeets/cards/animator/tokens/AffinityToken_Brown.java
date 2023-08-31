package eatyourbeets.cards.animator.tokens;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class AffinityToken_Brown extends AffinityToken
{
    public static final EYBCardData DATA = RegisterAffinityToken(AffinityToken_Brown.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Brown;

    public AffinityToken_Brown()
    {
        super(DATA, AFFINITY_TYPE);
    }
}