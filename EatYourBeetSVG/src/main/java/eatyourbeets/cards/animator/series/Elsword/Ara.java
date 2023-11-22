package eatyourbeets.cards.animator.series.Elsword;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ara extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Ara.class)
            .SetAttack(1, CardRarity.COMMON)
            
            .SetSeriesFromClassPackage();

    public Ara()
    {
        super(DATA);

        Initialize(5, 0, 2, 2);
        SetUpgrade(2, 0, 0);

        SetAffinity_Green(1);
        SetAffinity_Red(1);

        SetAffinityRequirement(Affinity.Green, 2);
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
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SPEAR).SetSoundPitch(1.1f, 1.3f);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SPEAR).SetSoundPitch(1.1f, 1.3f);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.GainRed(secondaryValue);
        }
    }
}