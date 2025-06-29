package eatyourbeets.relics.animator;

import eatyourbeets.relics.AnimatorRelic;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SoulGem extends AnimatorRelic
{
    public static final String ID = CreateFullID(SoulGem.class);

    public SoulGem()
    {
        super(ID, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public void atTurnStartPostDraw()
    {
        GameActions.Bottom.ExhaustFromHand(name, 1, true)
        .SetOptions(true, true, true)
        .SetFilter(GameUtilities::IsHindrance);

        flash();
    }
}