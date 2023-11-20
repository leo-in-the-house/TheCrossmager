package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MarisaKirisame extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MarisaKirisame.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public MarisaKirisame()
    {
        super(DATA);

        Initialize(1, 0, 8);
        SetUpgrade(1, 0, 0);

        SetAffinity_Yellow(2);

        SetEthereal(true);
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
        GameActions.Bottom.WaitRealtime(1.4f);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.VFX(VFX.ShootingStars(player.hb, player.hb.height));
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE);
        }

        GameActions.Bottom.EvokeOrb(2);
    }
}

