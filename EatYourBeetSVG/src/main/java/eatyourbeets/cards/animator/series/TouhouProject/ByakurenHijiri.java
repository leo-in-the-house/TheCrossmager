package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class ByakurenHijiri extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ByakurenHijiri.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage();

    public ByakurenHijiri()
    {
        super(DATA);

        Initialize(0, 0, 2, 6);
        SetUpgrade(0, 0, 1, 2);

        SetAffinity_Blue(1);
        SetAffinity_Dark(1);

        SetEthereal(true);

        SetAffinityRequirement(Affinity.Dark, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainRed(magicNumber, false);
        GameActions.Bottom.GainGreen(magicNumber, false);
        GameActions.Bottom.GainBlue(magicNumber, false);

        if (!CheckSpecialCondition(true))
        {
            GameActions.Bottom.TakeDamageAtEndOfTurn(secondaryValue, AttackEffects.DARK);
        }
    }
}