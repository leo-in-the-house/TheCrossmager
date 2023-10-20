package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;

public class Verdia extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Verdia.class)
            .SetSkill(3, CardRarity.COMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage();

    public Verdia()
    {
        super(DATA);

        Initialize(0, 12, 4);
        SetUpgrade(0, 8, 2);

        SetAffinity_Black(2);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainPlatedArmor(magicNumber);
    }
}