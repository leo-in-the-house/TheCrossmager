package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Arona extends AnimatorCard {
    public static final EYBCardData DATA = Register(Arona.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.Normal)
            .SetSeries(CardSeries.BlueArchive);

    public Arona() {
        super(DATA);

        Initialize(0, 0, 2, 2);
        SetUpgrade(0, 0, 1, 1);

        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainTeal(magicNumber);

        GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(m), secondaryValue);
    }
}