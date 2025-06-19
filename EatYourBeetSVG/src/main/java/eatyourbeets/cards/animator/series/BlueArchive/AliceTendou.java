package eatyourbeets.cards.animator.series.BlueArchive;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.DamageModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AliceTendou extends AnimatorCard {
    public static final EYBCardData DATA = Register(AliceTendou.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public AliceTendou() {
        super(DATA);

        Initialize(10, 0, 5);
        SetUpgrade(4, 0, 4);

        SetAffinity_Teal(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(VFX.SweepingBeam(p.hb, VFX.FlipHorizontally(), new Color(0.3f, 1f, 1f, 1f)), 0.1f);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true))
        {
            boolean hasLockOn = false;

            if (enemy.hasPower(LockOnPower.POWER_ID)) {
                hasLockOn = true;
            }

            GameActions.Top.DealDamage(this, enemy, AttackEffects.FIRE);

            if (hasLockOn) {
                DamageModifiers.For(this).Add(magicNumber);
            }
        }
    }
}