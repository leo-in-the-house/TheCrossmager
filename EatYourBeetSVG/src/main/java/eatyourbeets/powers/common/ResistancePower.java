package eatyourbeets.powers.common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Protect_1;
import eatyourbeets.cards.animator.special.Protect_2;
import eatyourbeets.cards.animator.special.Protect_3;
import eatyourbeets.powers.CommonClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.*;

public class ResistancePower extends CommonClickablePower
{
    public static final String POWER_ID = CreateFullID(ResistancePower.class);
    public static final int REQUIRED_AMOUNT = 2;
    protected int charge = 0;
    public ResistancePower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID, PowerTriggerConditionType.Special, REQUIRED_AMOUNT);

        this.canBeZero = true;
        this.triggerCondition.SetCondition(v -> this.charge >= v);
        this.triggerCondition.SetPayCost(this::ReduceCharge);
        this.triggerCondition.SetUses(-1, false, false);

        Initialize(amount);
    }

    @Override
    public String GetUpdatedDescription()
    {
        return FormatDescription(0, amount, REQUIRED_AMOUNT);
    }

    @Override
    public void update(int slot)
    {
        super.update(slot);

        if (GameUtilities.CanAcceptInput(false) && InputManager.Control.IsJustPressed())
        {
            TryClick();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        super.atEndOfTurn(isPlayer);

        if (enabled && amount > 0)
        {
            charge = charge + amount;

            flashWithoutSound();
        }
    }

    @Override
    protected ColoredString GetSecondaryAmount(Color c)
    {
        return new ColoredString(charge, Colors.Lerp(Color.LIGHT_GRAY, Settings.PURPLE_COLOR, charge, c.a));
    }

    public void ReduceCharge(int amount) {
        this.charge -= amount;
    }

    @Override
    public void OnUse(AbstractMonster m)
    {
        super.OnUse(m);

        RandomizedList<AbstractCard> protects = new RandomizedList<>();
        protects.Add(new Protect_1());
        protects.Add(new Protect_2());
        protects.Add(new Protect_3());

        GameActions.Bottom.MakeCardInHand(protects.Retrieve(rng));
        flashWithoutSound();
    }
}