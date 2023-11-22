package eatyourbeets.cards.animator.series.Elsword;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Rena extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Rena.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Rena()
    {
        super(DATA);

        Initialize(0, 4, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.GainBlur(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.GainRed(CombatStats.Affinities.GetAffinityLevel(Affinity.Red));
        GameActions.Bottom.GainGreen(CombatStats.Affinities.GetAffinityLevel(Affinity.Green));
        GameActions.Bottom.GainBlue(CombatStats.Affinities.GetAffinityLevel(Affinity.Blue));
    }
}