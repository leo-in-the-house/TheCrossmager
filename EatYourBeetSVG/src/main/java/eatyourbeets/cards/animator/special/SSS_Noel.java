package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SSS_Noel extends AnimatorCard {
    public static final EYBCardData DATA = Register(SSS_Noel.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public SSS_Noel() {
        super(DATA);

        Initialize(0, 7, 2);
        SetUpgrade(0, 3, 1);

        SetAffinity_Green(1, 0, 1);

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ReboundCards(magicNumber);
    }
}