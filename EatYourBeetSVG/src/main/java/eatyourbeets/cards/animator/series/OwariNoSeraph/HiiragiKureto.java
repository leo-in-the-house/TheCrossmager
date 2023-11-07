package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class HiiragiKureto extends AnimatorCard
{
    public static final EYBCardData DATA = Register(HiiragiKureto.class)
            .SetPower(1, CardRarity.RARE)
            .SetSeries(CardSeries.OwariNoSeraph);

    public HiiragiKureto()
    {
        super(DATA);

        Initialize(0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Yellow(1);

        SetInnate(true);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (startOfBattle && CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.ChangeStance(MagicStance.STANCE_ID);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new HiiragiKuretoPower(p, 1));
    }

    public static class HiiragiKuretoPower extends AnimatorClickablePower
    {
        public HiiragiKuretoPower(AbstractCreature owner, int amount)
        {
            super(owner, HiiragiKureto.DATA, PowerTriggerConditionType.Exhaust, 1);

            triggerCondition.SetUses(2, true, true);
            canBeZero = true;

            Initialize(amount);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            GameActions.Bottom.ChannelOrb(new Lightning());
        }
    }
}