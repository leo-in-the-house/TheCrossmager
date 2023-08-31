package eatyourbeets.cards.animator.tokens;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class AffinityToken_Pink extends AffinityToken
{
    public static final EYBCardData DATA = RegisterAffinityToken(AffinityToken_Pink.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Pink;

    public AffinityToken_Pink()
    {
        super(DATA, AFFINITY_TYPE);
    }
}