package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MetalBat extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MetalBat.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public MetalBat()
    {
        super(DATA);

        Initialize(3, 0, 1);
        SetUpgrade(3, 0, 1);

        SetAffinity_Violet(1);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        int multiplier = (int)(10 * (1 - GameUtilities.GetHealthPercentage(player)));

        SetScaling(Affinity.Violet, multiplier);

        super.Refresh(enemy);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.BLUNT_HEAVY);
    }
}