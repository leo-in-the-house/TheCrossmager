package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.replacement.TemporaryRetainPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TomoeKashiwaba extends AnimatorCard {
    public static final EYBCardData DATA = Register(TomoeKashiwaba.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public TomoeKashiwaba() {
        super(DATA);

        Initialize(8, 0, 0);
        SetUpgrade(4, 0, 0);

        SetAffinity_Red(1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        GameActions.Bottom.StackPower(new TemporaryRetainPower(p, 1));
    }
}