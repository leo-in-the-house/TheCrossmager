package eatyourbeets.cards.animator.series.BlueArchive;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class AruRikuhachima extends AnimatorCard {
    public static final EYBCardData DATA = Register(AruRikuhachima.class)
            .SetAttack(3, CardRarity.COMMON, EYBAttackType.Piercing, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public AruRikuhachima() {
        super(DATA);

        Initialize(14, 0, 2);
        SetUpgrade(8, 0, 2);

        SetAffinity_Violet(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameEffects.Queue.BorderFlash(Color.CORAL);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);

        GameActions.Bottom.ApplyVulnerable(TargetHelper.Normal(m), magicNumber);
        GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(m), magicNumber);
    }
}