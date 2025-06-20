package eatyourbeets.relics.animator;

import com.megacrit.cardcrawl.core.AbstractCreature;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.relics.AnimatorRelic;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class IronHorus extends AnimatorRelic implements OnBlockGainedSubscriber
{
    public static final String ID = CreateFullID(IronHorus.class);

    public IronHorus()
    {
        super(ID, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    protected void RefreshBattleEffect(boolean enabled)
    {
        super.RefreshBattleEffect(enabled);

        CombatStats.onBlockGained.ToggleSubscription(this, enabled);
    }

    @Override
    public void OnBlockGained(AbstractCreature creature, int block)
    {
        if (block > 0) {
            GameActions.Bottom.ApplyLockOn(TargetHelper.RandomEnemy(), 1);
        }
    }
}