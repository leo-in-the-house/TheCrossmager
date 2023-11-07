package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class Shinoa extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shinoa.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Shinoa()
    {
        super(DATA);

        Initialize(0, 5, 0);
        SetUpgrade(0, 9, 0);

        SetAffinity_Pink(1);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.ApplyVulnerable(TargetHelper.Enemies(), 1);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ApplyVulnerable(TargetHelper.Enemies(), 1);
    }
}