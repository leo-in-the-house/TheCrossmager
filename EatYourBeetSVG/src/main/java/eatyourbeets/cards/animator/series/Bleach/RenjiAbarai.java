package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;

public class RenjiAbarai extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RenjiAbarai.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Piercing, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public RenjiAbarai()
    {
        super(DATA);

        Initialize(7, 0);
        SetUpgrade(4, 0);

        SetAffinity_Green(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
    }
}