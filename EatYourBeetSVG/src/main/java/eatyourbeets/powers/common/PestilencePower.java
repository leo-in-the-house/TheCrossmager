package eatyourbeets.powers.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.actions.powers.ApplyPower;
import eatyourbeets.powers.CommonPower;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class PestilencePower extends CommonPower
{
    public static final String POWER_ID = CreateFullID(PestilencePower.class);

    public PestilencePower(AbstractCreature owner, int amount)
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
    public void onApplyPower(AbstractPower p, AbstractCreature target, AbstractCreature source)
    {
        super.onApplyPower(p, target, source);

        if (p.type == PowerType.DEBUFF && GameUtilities.IsCommonDebuff(p) && source.isPlayer)
        {
            p.amount += this.amount;

            final AbstractGameAction action = AbstractDungeon.actionManager.currentAction;
            if (action instanceof ApplyPower || action instanceof ApplyPowerAction)
            {
                action.amount += this.amount;
            }
            else
            {
                JUtils.LogWarning(this, "Unknown action type: " + action.getClass().getName());
            }
            this.flash();
        }
    }
}
