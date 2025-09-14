package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class ShizunaRemMitsurugi extends AnimatorCard {
    public static final EYBCardData DATA = Register(ShizunaRemMitsurugi.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public ShizunaRemMitsurugi() {
        super(DATA);

        Initialize(11, 0, 2);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Red(1);
        SetAffinity_Green(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);

        int lightnings = GameUtilities.GetOrbCount(Lightning.ORB_ID);

        if (lightnings > 0) {
            GameActions.Bottom.GainTemporaryStats(lightnings, 0, 0);
        }
    }
}