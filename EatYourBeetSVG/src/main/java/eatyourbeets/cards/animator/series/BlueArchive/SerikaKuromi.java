package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class SerikaKuromi extends AnimatorCard {
    public static final EYBCardData DATA = Register(SerikaKuromi.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public SerikaKuromi() {
        super(DATA);

        Initialize(8, 0, 2);
        SetUpgrade(2, 0, 0);

        SetAffinity_Red(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        boolean hitLockOn = false;

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            if (GameUtilities.GetCommonDebuffs(TargetHelper.Normal(enemy)).size() > 0) {
                if (enemy.hasPower(LockOnPower.POWER_ID)) {
                    hitLockOn = true;
                }

                GameActions.Bottom.DealDamage(this, enemy, AttackEffects.GUNSHOT);
            }
        }

        if (hitLockOn) {
            GameActions.Bottom.GainEnergyNextTurn(magicNumber);
        }
    }
}