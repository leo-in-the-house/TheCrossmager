package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Hinaichigo extends AnimatorCard {
    public static final EYBCardData DATA = Register(Hinaichigo.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public Hinaichigo() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Brown(1);

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ReduceStrength(m, magicNumber, true);
    }
}