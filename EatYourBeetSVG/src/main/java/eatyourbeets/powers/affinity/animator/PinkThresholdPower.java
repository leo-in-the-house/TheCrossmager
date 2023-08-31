package eatyourbeets.powers.affinity.animator;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.powers.affinity.AnimatorAffinityPower;

public class PinkThresholdPower extends AnimatorAffinityPower
{
    public static final String POWER_ID = CreateFullID(PinkThresholdPower.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Pink;
    public static final String SYMBOL = "PT";

    public PinkThresholdPower()
    {
        super(AFFINITY_TYPE, POWER_ID, SYMBOL);

        upgradeMin = 4;
    }

    @Override
    public PowerHelper GetThresholdBonusPower()
    {
        return PowerHelper.Knowledge;
    }
}