package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.SFX;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Boros extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Boros.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Boros()
    {
        super(DATA);

        Initialize(0, 2);
        SetCostUpgrade(-1);

        SetAffinity_Violet(1, 0, 1);
        SetAffinity_Pink(1, 0, 1);
        SetAffinity_Teal(1, 0, 1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.StackPower(new BorosPower(p, 1));
    }

    public static class BorosPower extends AnimatorPower
    {
        public BorosPower(AbstractCreature owner, int amount)
        {
            super(owner, Boros.DATA);

            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.Affinities.SetUseAdjacentAffinities(true);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.Affinities.SetUseAdjacentAffinities(false);
        }


        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void playApplyPowerSfx()
        {
            SFX.Play(SFX.ATTACK_MAGIC_SLOW_1, 0.65f, 0.75f, 0.85f);
            SFX.Play(SFX.ORB_LIGHTNING_EVOKE, 0.45f, 0.5f, 1.05f);
        }
    }
}