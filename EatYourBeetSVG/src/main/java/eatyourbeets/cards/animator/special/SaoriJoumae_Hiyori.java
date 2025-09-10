package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class SaoriJoumae_Hiyori extends AnimatorCard {
    public static final EYBCardData DATA = Register(SaoriJoumae_Hiyori.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.Normal)
            .SetSeries(CardSeries.BlueArchive);

    public SaoriJoumae_Hiyori() {
        super(DATA);

        Initialize(0, 7, 4);
        SetUpgrade(0, 2, 2);

        SetExhaust(true);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Blue(1, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(m), magicNumber);
    }
}