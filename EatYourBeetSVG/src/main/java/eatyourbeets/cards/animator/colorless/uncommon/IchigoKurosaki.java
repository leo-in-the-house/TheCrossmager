package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.IchigoBankai;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;

public class IchigoKurosaki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IchigoKurosaki.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Bleach)
            .PostInitialize(data -> data.AddPreview(new IchigoBankai(), false));

    public IchigoKurosaki()
    {
        super(DATA);

        Initialize(0, 0);

        SetAffinity_Red(1);
        SetAffinity_Green(1);

        SetExhaust(true);

        SetAffinityRequirement(Affinity.Red, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Draw(1);
        GameActions.Bottom.GainRed(1, upgraded);
        GameActions.Bottom.GainGreen(1, upgraded);

        if (CheckSpecialCondition(true))
        {
            GameActions.Bottom.MakeCardInDrawPile(new IchigoBankai());
        }
    }
}