package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SSS_Randy extends AnimatorCard {
    public static final EYBCardData DATA = Register(SSS_Randy.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public SSS_Randy() {
        super(DATA);

        Initialize(3, 0, 4);
        SetUpgrade(1, 0, 0);

        SetAffinity_Red(1, 0, 1);

        SetRetain(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AbstractGameAction.AttackEffect.SMASH);
        }

        if (GameUtilities.HasOrb(Lightning.ORB_ID)) {
            GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
        }
    }
}