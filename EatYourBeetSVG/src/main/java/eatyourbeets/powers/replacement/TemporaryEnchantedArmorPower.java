package eatyourbeets.powers.replacement;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.powers.animator.EnchantedArmorPlayerPower;
import eatyourbeets.powers.animator.EnchantedArmorPower;
import eatyourbeets.utilities.GameActions;

public class TemporaryEnchantedArmorPower extends EnchantedArmorPlayerPower implements CloneablePowerInterface
{
    public TemporaryEnchantedArmorPower(AbstractCreature owner, int amount)
    {
        super(owner, amount);

        this.ID = EnchantedArmorPower.POWER_ID;
    }

    @Override
    public void atStartOfTurnPostDraw()
    {
        super.atStartOfTurnPostDraw();

        GameActions.Bottom.RemovePower(owner, owner, this);
    }

    @Override
    public AbstractPower makeCopy()
    {
        return new TemporaryEnchantedArmorPower(owner, amount);
    }
}
