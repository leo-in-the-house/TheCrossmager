package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Souseiseki extends AnimatorCard {
    public static final EYBCardData DATA = Register(Souseiseki.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Piercing)
            .SetSeriesFromClassPackage();

    public Souseiseki() {
        super(DATA);

        Initialize(9, 0, 0);
        SetUpgrade(4, 0, 0);

        SetAffinity_Green(1);
    }

    @Override
    protected float ModifyDamage(AbstractMonster enemy, float amount)
    {
        amount += GameUtilities.GetOtherCardsInHand(this).size();

        return super.ModifyDamage(enemy, amount);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
    }
}