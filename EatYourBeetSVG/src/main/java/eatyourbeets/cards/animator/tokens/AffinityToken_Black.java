package eatyourbeets.cards.animator.tokens;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class AffinityToken_Black extends AffinityToken
{
    public static final EYBCardData DATA = RegisterAffinityToken(AffinityToken_Black.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Black;

    public AffinityToken_Black()
    {
        super(DATA, AFFINITY_TYPE);
    }
}