package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Sora_BattlePlan1 extends Sora_BattlePlan
{
    public static final EYBCardData DATA = Register(Sora_BattlePlan1.class)
            .SetAttack(0, CardRarity.SPECIAL, EYBAttackType.Ranged, EYBCardTarget.Normal)
            .SetImagePath(IMAGE_PATH)
            .SetSeries(SERIES);

    public Sora_BattlePlan1()
    {
        super(DATA);

        Initialize(3, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Red(1, 0, 1);
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
            GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);
        }

        GameActions.Bottom.GainStrength(1);
    }
}