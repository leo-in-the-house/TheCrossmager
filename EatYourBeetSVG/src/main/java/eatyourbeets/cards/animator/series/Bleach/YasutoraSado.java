package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;

public class YasutoraSado extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YasutoraSado.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Normal)
            .SetSeriesFromClassPackage();

    private static final CardEffectChoice choices = new CardEffectChoice();

    public YasutoraSado()
    {
        super(DATA);

        Initialize(6, 6, 2);
        SetUpgrade(3, 3, 0);

        SetAffinity_Brown(2);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        GameActions.Bottom.GainBlock(block);
    }
}