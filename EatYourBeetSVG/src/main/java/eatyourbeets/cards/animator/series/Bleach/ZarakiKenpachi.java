package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnAffinityThresholdReachedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.affinity.AbstractAffinityPower;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;

public class ZarakiKenpachi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ZarakiKenpachi.class)
            .SetPower(1, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public ZarakiKenpachi()
    {
        super(DATA);

        Initialize(0, 18, 2);
        SetUpgrade(0, 8, 0);

        SetAffinity_Red(2, 0, 0);
        SetAffinity_Black(1, 0, 0);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);

        GameActions.Bottom.StackPower(new ZarakiKenpachiPower(p, magicNumber));
    }

    public static class ZarakiKenpachiPower extends AnimatorPower implements OnAffinityThresholdReachedSubscriber
    {
        boolean activated;

        public ZarakiKenpachiPower(AbstractPlayer owner, int amount)
        {
            super(owner, ZarakiKenpachi.DATA);

            this.amount = amount;

            CombatStats.onAffinityThresholdReached.Subscribe(this);

            updateDescription();
        }

        @Override
        public void OnAffinityThresholdReached(AbstractAffinityPower power, int thresholdLevel)
        {
            if (power.affinity == Affinity.Red)
            {
                GameActions.Bottom.GainStrength(amount);
            }
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}