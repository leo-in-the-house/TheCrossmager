package eatyourbeets.cards.animatorClassic.series.Katanagatari;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animatorClassic.special.Shichika_Kyotouryuu;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;

public class Shichika extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Shichika.class).SetSeriesFromClassPackage().SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None);
    static
    {
        DATA.AddPreview(new Shichika_Kyotouryuu(), false);
    }

    public Shichika()
    {
        super(DATA);

        Initialize(0, 2);
        SetUpgrade(0, 0);
        SetScaling(0, 1, 1);

        SetExhaust(true);
        
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainRed(1, upgraded);
        GameActions.Bottom.GainGreen(1, upgraded);

        GameActions.Bottom.MakeCardInHand(new Shichika_Kyotouryuu());
    }
}