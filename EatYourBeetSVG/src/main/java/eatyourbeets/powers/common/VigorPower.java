package eatyourbeets.powers.common;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import eatyourbeets.powers.CommonPower;
import eatyourbeets.utilities.GameActions;

public class VigorPower extends CommonPower {
    public static final String POWER_ID = CreateFullID(VigorPower.class);

    public VigorPower(AbstractCreature owner, int amount) {
        super(owner, POWER_ID);

        Initialize(amount);
    }

    @Override
    public void updateDescription()
    {
        this.description = FormatDescription(0, amount);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        float newDamage = (type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage);

        return super.atDamageGive(newDamage, type, card);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            RemovePower(GameActions.Top);
        }
    }

    @Override
    public void atStartOfTurn()
    {
        RemovePower();
    }
}
