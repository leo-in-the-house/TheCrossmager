package eatyourbeets.relics.animator;

import eatyourbeets.relics.AnimatorRelic;

public class WitchsInvitation extends AnimatorRelic
{
    public static final String ID = CreateFullID(WitchsInvitation.class);

    public WitchsInvitation()
    {
        super(ID, RelicTier.SHOP, LandingSound.FLAT);
    }
}