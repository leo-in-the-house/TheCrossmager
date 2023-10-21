package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.DamageModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Krusty extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Krusty.class)
            .SetAttack(3, CardRarity.COMMON, EYBAttackType.Normal)
            .SetSeriesFromClassPackage();

    public Krusty()
    {
        super(DATA);

        Initialize(20, 0, 0);
        SetUpgrade(12, 0, 0);

        SetAffinity_Red(2);
        SetAffinity_Brown(1);

        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);
        GameActions.Bottom.ShakeScreen(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH);
        GameActions.Bottom.ModifyAllInstances(uuid, c ->
        {
            DamageModifiers.For(c).Add(damage);
            c.flash();
        });
    }
}