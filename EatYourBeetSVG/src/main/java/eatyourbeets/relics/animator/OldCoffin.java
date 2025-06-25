package eatyourbeets.relics.animator;

import eatyourbeets.relics.AnimatorRelic;
import eatyourbeets.utilities.GameActions;

public class OldCoffin extends AnimatorRelic
{
    public static final String ID = CreateFullID(OldCoffin.class);

    private boolean activated = false;

    public OldCoffin()
    {
        super(ID, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart()
    {
        super.atBattleStart();
        activated = false;
    }

    public void atTurnStartPostDraw() {
        if (!this.activated) {
            this.activated = true;
            this.flash();
            GameActions.Bottom.Cycle(name, 99)
                    .SetOptions(true, true, true);
        }

    }
}