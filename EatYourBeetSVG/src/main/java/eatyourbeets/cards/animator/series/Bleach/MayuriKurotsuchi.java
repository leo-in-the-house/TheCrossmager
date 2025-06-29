package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class MayuriKurotsuchi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MayuriKurotsuchi.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public MayuriKurotsuchi()
    {
        super(DATA);

        Initialize(0, 0, 7, 0);
        SetUpgrade(0, 0, 3, 0);

        SetAffinity_Violet(1);
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ApplyPoison(TargetHelper.Normal(m), magicNumber);
    }
}