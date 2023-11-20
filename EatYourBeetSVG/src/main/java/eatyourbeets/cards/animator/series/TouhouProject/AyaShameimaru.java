package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AyaShameimaru extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AyaShameimaru.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.Self)
            .SetSeriesFromClassPackage();

    public AyaShameimaru()
    {
        super(DATA);

        Initialize(0, 0, 2, 0);
        SetUpgrade(0, 0, 2, 0);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Blue(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainTemporaryStats(0, magicNumber, 0);
        GameActions.Bottom.Draw(1);
    }
}

