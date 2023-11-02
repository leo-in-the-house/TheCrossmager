package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Genos extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Genos.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage();

    public Genos()
    {
        super(DATA);

        Initialize(12, 0);
        SetUpgrade(4, 0);

        SetAffinity_Red(1);
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        GameActions.Bottom.IncreaseScaling(this, Affinity.Red, 1);
        GameActions.Bottom.Callback(() -> {
            Refresh(null);
        });
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
    }
}