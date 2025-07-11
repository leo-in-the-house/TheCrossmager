package eatyourbeets.powers.affinity.animator;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.powers.affinity.AnimatorAffinityPower;

public class BrownThresholdPower extends AnimatorAffinityPower
{
    public static final String POWER_ID = CreateFullID(BrownThresholdPower.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Brown;
    public static final String SYMBOL = "OT";

    public BrownThresholdPower()
    {
        super(AFFINITY_TYPE, POWER_ID, SYMBOL);

        upgradeMin = 3;
    }

    @Override
    public PowerHelper GetThresholdBonusPower()
    {
        return PowerHelper.Resistance;
    }
}