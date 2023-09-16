package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.SFX;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;

public class OrbCore_Water extends OrbCore
{
    public static final EYBCardData DATA = RegisterOrbCore(OrbCore_Water.class, GR.Tooltips.Water)
            .SetPower(2, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS);
    public static final int POWER_ACTIVATION_COST = 2;
    public static final int BLOCK_TEMP_HP_AMOUNT = 8;

    public OrbCore_Water()
    {
        super(DATA, Water::new, 1);

        Initialize(0, 0, BLOCK_TEMP_HP_AMOUNT, POWER_ACTIVATION_COST);

        SetAffinity_Blue(2);
    }

    public void ChannelOrb()
    {
        GameActions.Bottom.ChannelOrb(new Water());
    }

    public void ApplyPower()
    {
        GameActions.Bottom.StackPower(new OrbCore_Water.OrbCore_WaterPower(player, 1));
    }

    public static class OrbCore_WaterPower extends AnimatorClickablePower
    {
        public OrbCore_WaterPower(AbstractCreature owner, int amount)
        {
            super(owner, OrbCore_Water.DATA, PowerTriggerConditionType.Energy, POWER_ACTIVATION_COST);

            this.triggerCondition.SetUses(amount, false, true);

            Initialize(amount);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, triggerCondition.requiredAmount, BLOCK_TEMP_HP_AMOUNT);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            SFX.Play(SFX.ANIMATOR_ORB_WATER_CHANNEL);

            GameActions.Bottom.GainBlock(BLOCK_TEMP_HP_AMOUNT);
            GameActions.Bottom.GainTemporaryHP(BLOCK_TEMP_HP_AMOUNT);

            ReducePower(GameActions.Last, 1);
            SetEnabled(false);
        }

        @Override
        public void atStartOfTurnPostDraw()
        {
            super.atStartOfTurnPostDraw();

            SetEnabled(true);
        }
    }
}