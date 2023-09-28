package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.Strike;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class FirisMistlud extends AnimatorCard {
    public static final EYBCardData DATA = Register(FirisMistlud.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    static
    {
        DATA.AddPreview(new Strike(), false);
    }

    public FirisMistlud() {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 0);

        SetExhaust(true);

        SetAffinity_Brown(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.MakeCardInHand(new Strike())
            .Repeat(magicNumber)
            .AddCallback(card -> {
                 CostModifiers.For(card).Set(0);
            });
    }
}