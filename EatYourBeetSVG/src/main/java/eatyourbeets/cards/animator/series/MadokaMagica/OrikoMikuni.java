package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;

public class OrikoMikuni extends AnimatorCard
{
    public static final EYBCardData DATA = Register(OrikoMikuni.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage()
            .ObtainableAsReward((data, deck) -> (deck.size() >= 18));

    public OrikoMikuni()
    {
        super(DATA);

        Initialize(0, 0, 3, 3);
        SetUpgrade(0, 0, 0, -1);

        SetAffinity_White(1);
        SetAffinity_Black(1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.Scry(magicNumber)
        .AddCallback(cards ->
        {
            int discarded = 0;
            for (AbstractCard c : cards)
            {
                if (!GameUtilities.IsHindrance(c))
                {
                    discarded += 1;
                }
            }

            if (discarded > 0)
            {
                GameActions.Bottom.TakeDamageAtEndOfTurn(discarded * secondaryValue);
            }
        });
        GameActions.Bottom.Draw(1);
    }
}