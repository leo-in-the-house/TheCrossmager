package eatyourbeets.cards.animator.tokens;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCardData;

public class AffinityToken_Violet extends AffinityToken
{
    public static final EYBCardData DATA = RegisterAffinityToken(AffinityToken_Violet.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Violet;

    public AffinityToken_Violet()
    {
        super(DATA, AFFINITY_TYPE);
    }
}