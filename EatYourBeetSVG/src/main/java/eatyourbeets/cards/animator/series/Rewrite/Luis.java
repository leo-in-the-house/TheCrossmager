package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Luis extends AnimatorCard {
    public static final EYBCardData DATA = Register(Luis.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Piercing, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Luis() {
        super(DATA);

        Initialize(5, 0, 0);
        SetUpgrade(4, 0, 0);

        SetRetain(true);

        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SPEAR);

        GameActions.Bottom.ChangeStance(CalmStance.STANCE_ID);
    }
}