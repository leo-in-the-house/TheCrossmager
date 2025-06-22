package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class HikariTachibana_Nozomi extends AnimatorCard {
    public static final EYBCardData DATA = Register(HikariTachibana_Nozomi.class)
            .SetAttack(X_COST, CardRarity.SPECIAL, EYBAttackType.Ranged, EYBCardTarget.Normal)
            .SetSeries(CardSeries.BlueArchive);

    public HikariTachibana_Nozomi() {
        super(DATA);

        Initialize(11, 0, 2);
        SetUpgrade(3, 0, 0);

        SetFading(true);

        SetAffinity_Yellow(1);
        SetAffinity_Green(1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(GameUtilities.GetPotentialXCostEnergy(this));
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        int xCostEnergy = GameUtilities.UseXCostEnergy(this);

        if (xCostEnergy > 0) {
            for (int i=0; i<xCostEnergy; i++) {
                GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);
                GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(m), magicNumber);
            }
        }
    }
}