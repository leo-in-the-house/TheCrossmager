package eatyourbeets.powers.affinity.animator;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.powers.affinity.AnimatorAffinityPower;

public class CorruptionPower extends AnimatorAffinityPower
{
    public static final String POWER_ID = CreateFullID(CorruptionPower.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Black;
    public static final String SYMBOL = "C";

    public CorruptionPower()
    {
        super(AFFINITY_TYPE, POWER_ID, SYMBOL);

        upgradeMin = 5;
    }

    @Override
    public PowerHelper GetThresholdBonusPower()
    {
        return PowerHelper.Insanity;
    }
}