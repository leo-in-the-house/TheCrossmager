package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.SFX;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;

public class ShikizakiKiki extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(ShikizakiKiki.class)
            .SetPower(2, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Katanagatari);
    public static final int POWER_ENERGY_COST = 2;

    public ShikizakiKiki()
    {
        super(DATA);

        Initialize(0, 0, 0, POWER_ENERGY_COST);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Yellow(1);
        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new ShikizakiKikiPower(p, magicNumber));
    }

    public static class ShikizakiKikiPower extends AnimatorClickablePower
    {
        public ShikizakiKikiPower(AbstractCreature owner, int amount)
        {
            super(owner, ShikizakiKiki.DATA, PowerTriggerConditionType.Energy, POWER_ENERGY_COST);

            triggerCondition.SetUses(1, true, false);

            Initialize(amount);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, triggerCondition.requiredAmount);
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            CombatStats.Affinities.AddAffinitySealUses(1);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            int energyGain = 0;

            for (AbstractCard c : player.hand.group)
            {
                if (GameUtilities.IsSealed(c)) {
                    GameActions.Bottom.MakeCardInHand(c.makeStatEquivalentCopy());
                    energyGain++;
                }
            }

            if (energyGain >= 0) {
                GameActions.Bottom.GainEnergy(energyGain);
            }

            GameActions.Bottom.SFX(SFX.CARD_UPGRADE, 0.5f, 0.6f).SetDuration(0.25f, true);
            GameActions.Bottom.SFX(SFX.ATTACK_FIRE, 0.5f, 0.6f).SetDuration(0.25f, true);
            GameActions.Bottom.SFX(SFX.ATTACK_AXE, 0.5f, 0.6f).SetDuration(0.25f, true);
            GameActions.Bottom.SFX(SFX.CARD_UPGRADE, 0.5f, 0.6f).SetDuration(0.25f, true);
        }
    }
}