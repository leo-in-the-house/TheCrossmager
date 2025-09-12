package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class SwinAbel extends AnimatorCard {
    public static final EYBCardData DATA = Register(SwinAbel.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public SwinAbel() {
        super(DATA);

        Initialize(10, 0, 3);
        SetUpgrade(4, 0, 0);

        SetAffinity_Green(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
        GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(m), magicNumber);
    }
}