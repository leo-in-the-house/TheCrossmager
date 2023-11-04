package eatyourbeets.cards.animator.series.Overlord;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.SadisticPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.SFX;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ainz extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Ainz.class)
            .SetPower(9, CardRarity.RARE)

            .SetSeriesFromClassPackage();

    private static int SADISTIC_MULTIPLIER = 7;

    public Ainz()
    {
        super(DATA);

        Initialize(0, 0, SADISTIC_MULTIPLIER);
        SetCostUpgrade(-2);

        SetAffinity_Blue(2);
        SetAffinity_Violet(2);
        SetAffinity_Black(2);
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        GameActions.Bottom.Motivate(this, 1);
    }

    @Override
    public void triggerWhenDrawn()
    {
        super.triggerWhenDrawn();

        GameActions.Bottom.Motivate(this, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new AinzPower(p, 1));
    }

    public static class AinzPower extends AnimatorPower
    {
        public AinzPower(AbstractPlayer owner, int amount)
        {
            super(owner, Ainz.DATA);

            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount, SADISTIC_MULTIPLIER * amount );
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            GameActions.Bottom.SFX(SFX.ORB_LIGHTNING_EVOKE, 0.9f, 1.1f);
            GameActions.Bottom.BorderLongFlash(Color.valueOf("3d0066"));
            GameActions.Bottom.SFX(SFX.ORB_DARK_EVOKE, 0.9f, 1.1f);
        }


        @Override
        public void onApplyPower(AbstractPower p, AbstractCreature target, AbstractCreature source)
        {
            super.onApplyPower(p, target, source);

            if (GameUtilities.IsCommonDebuff(p) && source == owner && !target.hasPower(ArtifactPower.POWER_ID))
            {
                GameActions.Bottom.GainStrength(amount);
                GameActions.Bottom.GainDexterity(amount);
                GameActions.Bottom.StackPower(new SadisticPower(player, amount * SADISTIC_MULTIPLIER));

                this.flash();
            }
        }
    }
}