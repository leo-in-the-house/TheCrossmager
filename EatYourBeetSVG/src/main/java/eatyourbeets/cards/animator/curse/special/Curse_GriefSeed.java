package eatyourbeets.cards.animator.curse.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Curse_GriefSeed extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Curse_GriefSeed.class)
            .SetCurse(-2, EYBCardTarget.None, true)
            .SetSeries(CardSeries.MadokaMagica);

    public Curse_GriefSeed()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Black(1);

        SetEndOfTurnPlay(false);
        SetUnplayable(true);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.GainTemporaryHP(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
    }
}
