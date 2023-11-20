package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class ByakurenHijiri extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ByakurenHijiri.class)
            .SetSkill(0, CardRarity.RARE, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage();

    public ByakurenHijiri()
    {
        super(DATA);

        Initialize(0, 0, 2, 6);
        SetUpgrade(0, 0, 1, 2);

        SetAffinity_Blue(1);
        SetAffinity_Black(1);

        SetEthereal(true);

        SetAffinityRequirement(Affinity.Black, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainRed(magicNumber, false);
        GameActions.Bottom.GainGreen(magicNumber, false);
        GameActions.Bottom.GainBlue(magicNumber, false);

        if (!CheckSpecialCondition(false))
        {
            GameActions.Bottom.TakeDamageAtEndOfTurn(secondaryValue, AttackEffects.DARK);
        }
    }
}