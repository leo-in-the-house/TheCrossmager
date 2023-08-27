package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Yusa extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Yusa.class).SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Yusa()
    {
        super(DATA);

        Initialize(0, 8, 2, 0);
        SetUpgrade(0, 2, 1, 0);

        SetAffinity_Blue(1);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Scry(magicNumber);
    }
}