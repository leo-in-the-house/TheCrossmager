package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;

public class Protect_2 extends AnimatorCard {
    public static final EYBCardData DATA = Register(Protect_2.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None);

    public Protect_2() {
        super(DATA);

        Initialize(0, 8, 1);
        SetUpgrade(0, 3, 1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainArtifact(magicNumber);
    }
}