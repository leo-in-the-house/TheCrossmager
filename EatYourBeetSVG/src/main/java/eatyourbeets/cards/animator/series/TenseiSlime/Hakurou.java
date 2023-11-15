package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Hakurou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Hakurou.class)
            .SetSkill(3, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Hakurou()
    {
        super(DATA);

        Initialize(0, 18);
        SetUpgrade(0, 12);

        SetAffinity_Green(2);

        SetAffinityRequirement(Affinity.Green, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
        }
    }
}