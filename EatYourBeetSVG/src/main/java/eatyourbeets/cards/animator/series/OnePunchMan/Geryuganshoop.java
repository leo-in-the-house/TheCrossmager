package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Geryuganshoop extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Geryuganshoop.class)
            .SetPower(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Geryuganshoop()
    {
        super(DATA);

        Initialize(0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Pink(2);
        SetAffinity_Yellow(2);

        SetEthereal(true);
        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new GeryuganshoopPower(player, 1));
    }

    public static class GeryuganshoopPower extends AnimatorPower
    {
        public GeryuganshoopPower(AbstractCreature owner, int amount)
        {
            super(owner, Geryuganshoop.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.setEnableConsumingStatsWithScalingCard(false);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.setEnableConsumingStatsWithScalingCard(true);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}