package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Kuroyukihime_BlackLotus;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kuroyukihime extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Kuroyukihime.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.AccelWorld)
            .PostInitialize(data -> data.AddPreview(new Kuroyukihime_BlackLotus(), true));

    public Kuroyukihime()
    {
        super(DATA);

        Initialize(0, 0, 2);

        SetAffinity_Green(2);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DiscardFromHand(name, magicNumber, false)
        .SetOptions(false, false, false)
        .AddCallback(() ->
        {//
            GameActions.Bottom.MakeCardInHand(new Kuroyukihime_BlackLotus())
                    .SetUpgrade(upgraded, true);
        });
    }
}