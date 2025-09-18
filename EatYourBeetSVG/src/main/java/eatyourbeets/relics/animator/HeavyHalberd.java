package eatyourbeets.relics.animator;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.interfaces.subscribers.OnAffinityGainedSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.relics.AnimatorRelic;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class HeavyHalberd extends AnimatorRelic implements OnAffinityGainedSubscriber
{
    public static final String ID = CreateFullID(HeavyHalberd.class);
    public static final int VULNERABLE_AMOUNT = 2;
    public static final int DRAW_AMOUNT = 2;

    public HeavyHalberd()
    {
        super(ID, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription()
    {
        return FormatDescription(0, VULNERABLE_AMOUNT, DRAW_AMOUNT);
    }

    @Override
    protected void RefreshBattleEffect(boolean enabled)
    {
        super.RefreshBattleEffect(enabled);

        CombatStats.onAffinityGained.ToggleSubscription(this, enabled);
    }

    @Override
    public int OnAffinityGained(Affinity affinity, int amount)
    {
        if (affinity == Affinity.Red)
        {
            GameActions.Bottom.ApplyVulnerable(TargetHelper.Enemies(player), VULNERABLE_AMOUNT);
            flash();
        }
        else if (affinity == Affinity.Green)
        {
            GameActions.Bottom.Draw(DRAW_AMOUNT);
            flash();
        }

        return amount;
    }
}