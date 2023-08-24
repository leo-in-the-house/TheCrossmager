package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;

public class AngelAlter extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(AngelAlter.class).SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None).SetColor(CardColor.COLORLESS).SetSeries(CardSeries.AngelBeats);

    public AngelAlter()
    {
        super(DATA);

        Initialize(0, 0, 2, 0);
        SetAffinity_Red(1);
        SetAffinity_Light(1);
        SetHaste(true);
        SetEthereal(true);
        SetUnplayable(true);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.GainRed(magicNumber);
        GameActions.Bottom.GainLight(magicNumber);

        AbstractCard copy = this.makeCopy();

        if (upgraded) {
            copy.magicNumber = copy.magicNumber * copy.magicNumber * copy.magicNumber;
        }
        else {
            copy.magicNumber *= copy.magicNumber;
        }
        if (copy.magicNumber > 999) {
            //Let's not get too crazy now ;)
            copy.magicNumber = 999;
        }

        GameActions.Bottom.MakeCardInDrawPile(copy);
    }
}