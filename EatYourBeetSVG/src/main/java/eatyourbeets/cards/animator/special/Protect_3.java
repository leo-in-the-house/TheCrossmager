package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;

public class Protect_3 extends AnimatorCard {
    public static final EYBCardData DATA = Register(Protect_3.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None);

    public Protect_3() {
        super(DATA);

        Initialize(0, 6, 4);
        SetUpgrade(0, 2, 2);

        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return (magicNumber > 0) ? TempHPAttribute.Instance.SetCard(this, true) : null;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryHP(magicNumber);
    }
}