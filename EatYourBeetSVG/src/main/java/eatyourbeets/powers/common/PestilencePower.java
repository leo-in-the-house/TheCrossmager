package eatyourbeets.powers.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.CommonPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

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
            if (!target.isPlayer) {
                GameActions.Top.VFX(VFX.ThrowDagger(target.hb, 0.2f));
                GameActions.Top.DealDamage(player, target, 2 * this.amount, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.NONE);
            }
        }
    }
}
