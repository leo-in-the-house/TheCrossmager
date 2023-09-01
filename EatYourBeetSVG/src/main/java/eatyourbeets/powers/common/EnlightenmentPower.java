package eatyourbeets.powers.common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import eatyourbeets.powers.CommonPower;
import eatyourbeets.utilities.ColoredString;
import eatyourbeets.utilities.Colors;
import eatyourbeets.utilities.GameActions;

public class EnlightenmentPower extends CommonPower
{
    public static final String POWER_ID = CreateFullID(EnlightenmentPower.class);


    protected int charge = 0;

    public EnlightenmentPower(AbstractCreature owner, int amount)
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
    protected ColoredString GetSecondaryAmount(Color c)
    {
        return new ColoredString(charge, Colors.Lerp(Color.LIGHT_GRAY, Settings.PURPLE_COLOR, charge, c.a));
    }

    @Override
    public void atStartOfTurn()
    {
        super.atStartOfTurn();

        if (amount > 0 && charge == 0)
        {
            GameActions.Bottom.GainEnergy(amount);
            charge = 1;
            flashWithoutSound();
        }
        else {
            charge--;
        }
    }
}
