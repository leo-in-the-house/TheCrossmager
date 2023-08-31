package eatyourbeets.powers.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Protect_1;
import eatyourbeets.cards.animator.special.Protect_2;
import eatyourbeets.cards.animator.special.Protect_3;
import eatyourbeets.powers.CommonClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.InputManager;
import eatyourbeets.utilities.RandomizedList;

public class ResistancePower extends CommonClickablePower
{
    public static final String POWER_ID = CreateFullID(ResistancePower.class);
    public static final int REQUIRED_AMOUNT = 1;

    public ResistancePower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID, PowerTriggerConditionType.Special, REQUIRED_AMOUNT);

        this.canBeZero = true;
        this.triggerCondition.SetCondition(v -> this.amount >= v);
        this.triggerCondition.SetPayCost(this::ReducePower);
        this.triggerCondition.SetUses(-1, false, false);

        Initialize(amount);
    }

    @Override
    public String GetUpdatedDescription()
    {
        return FormatDescription(0, REQUIRED_AMOUNT);
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