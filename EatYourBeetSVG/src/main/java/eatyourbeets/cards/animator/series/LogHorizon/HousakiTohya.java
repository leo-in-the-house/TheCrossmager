package eatyourbeets.cards.animator.series.LogHorizon;

import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.utilities.GameActions;

public class HousakiTohya extends AnimatorCard
{
    public static final EYBCardData DATA = Register(HousakiTohya.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal)
            .SetSeriesFromClassPackage();

    public HousakiTohya()
    {
        super(DATA);

        Initialize(3, 0, 3, 1);
        SetUpgrade(1, 0, 0, 1);

        SetAffinity_White(1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        }

        GameActions.Bottom.ApplyVulnerable(player, m, secondaryValue);
    }
}