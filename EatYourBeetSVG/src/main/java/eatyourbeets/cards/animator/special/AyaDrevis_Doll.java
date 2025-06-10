package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AyaDrevis_Doll extends AnimatorCard {
    public static final EYBCardData DATA = Register(AyaDrevis_Doll.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.MadFather);

    public AyaDrevis_Doll() {
        super(DATA);

        Initialize(0, 8, 3);
        SetUpgrade(0, 0, 3);

        SetAffinity_Brown(1, 0, 1);
        SetAffinity_Black(1);

        SetRetain(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainBrown(magicNumber);
        GameActions.Bottom.GainBlack(magicNumber);
    }
}