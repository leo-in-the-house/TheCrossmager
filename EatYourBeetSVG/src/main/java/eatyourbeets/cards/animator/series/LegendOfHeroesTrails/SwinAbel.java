package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class SwinAbel extends AnimatorCard {
    public static final EYBCardData DATA = Register(SwinAbel.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public SwinAbel() {
        super(DATA);

        Initialize(3, 0, 2, 3);
        SetUpgrade(4, 0, 0);

        SetAffinity_Green(1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HORIZONTAL);

        GameActions.Bottom.ChannelOrb(new Lightning());
        GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(m), magicNumber);
    }
}