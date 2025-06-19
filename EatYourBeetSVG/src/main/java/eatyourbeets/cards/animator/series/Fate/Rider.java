package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Rider extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Rider.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Rider()
    {
        super(DATA);

        Initialize(0, 6, 7, 2);
        SetUpgrade(0, 4, 2);

        SetAffinity_Green(1);
        SetAffinity_Black(1);

        SetAffinityRequirement(Affinity.Black, 4);
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        super.OnDrag(m);

        if (m != null)
        {
            GameUtilities.GetIntent(m).AddStrength(-magicNumber);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        if (CheckSpecialCondition(false)) {
            for (AbstractMonster target : GameUtilities.GetEnemies(true)) {
                GameActions.Bottom.ReduceStrength(target, magicNumber, true);
            }

            GameActions.Bottom.ApplyLockOn(TargetHelper.Enemies(), secondaryValue);
        }
    }
}