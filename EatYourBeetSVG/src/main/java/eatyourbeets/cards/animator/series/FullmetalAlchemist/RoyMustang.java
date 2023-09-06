package eatyourbeets.cards.animator.series.FullmetalAlchemist;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.orbs.animator.Fire;
import eatyourbeets.powers.common.BurningPower;
import eatyourbeets.utilities.GameActions;

public class RoyMustang extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RoyMustang.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();
    public static final int BURNING_ATTACK_BONUS = 25;

    public RoyMustang()
    {
        super(DATA);

        Initialize(9, 0, 0, BURNING_ATTACK_BONUS);
        SetUpgrade(2, 0, 0);

        SetAffinity_Blue(1, 1, 1);
        SetAffinity_White(1);
        SetAffinity_Red(1, 1, 0);

        SetEvokeOrbCount(1);

        SetAffinityRequirement(Affinity.Red, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.FIRE);
        GameActions.Bottom.ChannelOrbs(Fire::new, Math.min(p.orbs.size(), GameUtilities.GetEnemies(true).size()));

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.Callback(() -> BurningPower.AddPlayerAttackBonus(BURNING_ATTACK_BONUS));
        }
    }
}