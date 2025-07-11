package eatyourbeets.powers.affinity.animator;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.powers.affinity.AnimatorAffinityPower;

public class YellowThresholdPower extends AnimatorAffinityPower
{
    public static final String POWER_ID = CreateFullID(YellowThresholdPower.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Yellow;
    public static final String SYMBOL = "YT";

    public YellowThresholdPower()
    {
        super(AFFINITY_TYPE, POWER_ID, SYMBOL);

        upgradeMin = 3;
    }

    @Override
    public PowerHelper GetThresholdBonusPower()
    {
        return PowerHelper.Supercharge;
    }
}