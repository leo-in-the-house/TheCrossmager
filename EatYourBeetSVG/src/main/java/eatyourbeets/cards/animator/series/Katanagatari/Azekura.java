package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Azekura extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Azekura.class)
            .SetSkill(3, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Azekura()
    {
        super(DATA);

        Initialize(0, 18, 5);
        SetUpgrade(0, 12, 0);

        SetAffinity_Red(2);
        SetAffinity_Blue(1);
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        GameActions.Bottom.GainPlatedArmor(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
    }
}