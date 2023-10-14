package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.ThrowingKnife;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class EriShiina extends AnimatorCard
{
    public static final EYBCardData DATA = Register(EriShiina.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Piercing)
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

        Initialize(4, 0, 2, 0);
        SetUpgrade(2, 0, 1, 0);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);

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

        GameActions.Bottom.DealDamage(this, m, AttackEffects.DAGGER);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.DAGGER);

        GameActions.Bottom.CreateThrowingKnives(magicNumber);
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Bottom.CreateThrowingKnives(magicNumber);
    }

}