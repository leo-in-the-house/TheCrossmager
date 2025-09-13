package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class SSS_Tio extends AnimatorCard {
    public static final EYBCardData DATA = Register(SSS_Tio.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.Normal)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public SSS_Tio() {
        super(DATA);

        Initialize(8, 0, 2);
        SetUpgrade(4, 0, 0);

        SetAffinity_Teal(1, 0, 1);

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, GR.Enums.AttackEffect.ICE);
        GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(m), magicNumber);
        GameActions.Bottom.GainPlatedArmor(magicNumber);
    }
}