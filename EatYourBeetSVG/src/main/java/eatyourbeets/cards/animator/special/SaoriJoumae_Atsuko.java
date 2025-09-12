package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SaoriJoumae_Atsuko extends AnimatorCard {
    public static final EYBCardData DATA = Register(SaoriJoumae_Atsuko.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.BlueArchive);

    public SaoriJoumae_Atsuko() {
        super(DATA);

        Initialize(0, 0, 6);
        SetUpgrade(0, 0, 4);

        SetAffinity_Green(1, 0, 0);
        SetAffinity_White(1);

        SetExhaust(true);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(new RainbowCardEffect());
        GameActions.Bottom.Heal(magicNumber);
    }
}