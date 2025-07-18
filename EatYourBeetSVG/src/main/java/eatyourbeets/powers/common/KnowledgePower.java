package eatyourbeets.powers.common;

import com.megacrit.cardcrawl.core.AbstractCreature;
import eatyourbeets.powers.CommonPower;
import eatyourbeets.utilities.GameActions;

public class KnowledgePower extends CommonPower
{
    public static final String POWER_ID = CreateFullID(KnowledgePower.class);

    public KnowledgePower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        Initialize(amount);
    }

    @Override
    public void updateDescription()
    {
        this.description = FormatDescription(0, amount);
    }

    @Override
    public void atStartOfTurn()
    {
        super.atStartOfTurn();

        if (amount > 0)
        {
            GameActions.Bottom.Draw(amount);
            flashWithoutSound();
        }
    }
}
