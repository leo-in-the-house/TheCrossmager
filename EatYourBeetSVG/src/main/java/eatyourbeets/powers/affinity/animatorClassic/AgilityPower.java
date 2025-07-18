package eatyourbeets.powers.affinity.animatorClassic;

import eatyourbeets.cards.base.Affinity;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.powers.affinity.AnimatorClassicAffinityPower;

public class AgilityPower extends AnimatorClassicAffinityPower
{
    public static final String POWER_ID = CreateFullID(AgilityPower.class);
    public static final Affinity AFFINITY_TYPE = Affinity.Green;
    public static final String SYMBOL = "A";

    public AgilityPower()
    {
        super(AFFINITY_TYPE, POWER_ID, SYMBOL);
    }

    @Override
    public PowerHelper GetThresholdBonusPower()
    {
        return PowerHelper.Dexterity;
    }
}