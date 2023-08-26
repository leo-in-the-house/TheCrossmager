package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.ThrowingKnife;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class EriShiina extends AnimatorCard
{
    public static final EYBCardData DATA = Register(EriShiina.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal)
            .SetSeriesFromClassPackage();
    static
    {
        for (ThrowingKnife knife : ThrowingKnife.GetAllCards())
        {
            DATA.AddPreview(knife, false);
        }
    }

    public EriShiina()
    {
        super(DATA);

        Initialize(7, 0, 2, 0);
        SetUpgrade(2, 0, 1, 0);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Dark(1, 0, 1);

        SetEthereal(true);
        
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Bottom.CreateThrowingKnives(magicNumber);
    }

}