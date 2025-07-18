package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;

public class AngelAlter extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(AngelAlter.class).SetSkill(-2, CardRarity.SPECIAL, EYBCardTarget.None).SetColor(CardColor.COLORLESS).SetSeries(CardSeries.AngelBeats);

    public AngelAlter()
    {
        super(DATA);

        Initialize(0, 0, 2, 0);

        SetAffinity_Red(1);
        SetAffinity_Blue(1);
        SetAffinity_Violet(1);

        SetHaste(true);
        SetEthereal(true);
        SetUnplayable(true);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.GainRed(magicNumber);
        GameActions.Bottom.GainBlue(magicNumber);
        GameActions.Bottom.GainViolet(magicNumber);

        AbstractCard copy = this.makeCopy();

        GameActions.Bottom.MakeCardInDiscardPile(copy)
        .SetUpgrade(upgraded, true)
        .AddCallback(c -> {
            c.baseMagicNumber = Math.min(this.magicNumber, 999);

            int magicNumberIncrease = this.magicNumber;

            if (upgraded) {
                magicNumberIncrease *= this.magicNumber;
            }

            GameUtilities.IncreaseMagicNumber(c, Math.min(magicNumberIncrease, 999), false);
        });
    }
}