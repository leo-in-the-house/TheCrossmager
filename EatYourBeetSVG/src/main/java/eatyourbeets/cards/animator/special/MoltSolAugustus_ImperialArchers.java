package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.GATE.MoltSolAugustus;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class MoltSolAugustus_ImperialArchers extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MoltSolAugustus_ImperialArchers.class)
            .SetAttack(0, CardRarity.SPECIAL, EYBAttackType.Ranged)
            .SetSeries(MoltSolAugustus.DATA.Series);

    public MoltSolAugustus_ImperialArchers()
    {
        super(DATA);

        Initialize(4, 4, 0, 0);
        SetUpgrade(2, 2, 0, 0);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Brown(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ApplyWeak(TargetHelper.Normal(m), 1);
        GameActions.Bottom.ApplyVulnerable(TargetHelper.Normal(m), 1);
    }
}