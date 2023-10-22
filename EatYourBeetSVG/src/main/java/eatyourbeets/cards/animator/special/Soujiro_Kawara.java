package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.LogHorizon.Soujiro;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class Soujiro_Kawara extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Soujiro_Kawara.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Normal)
            .SetSeries(Soujiro.DATA.Series);

    public Soujiro_Kawara()
    {
        super(DATA);

        Initialize(3, 3, 2);
        SetUpgrade(2, 2, 0);

        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);

        GameActions.Bottom.ModifyAllCopies(Soujiro.DATA.ID)
        .AddCallback(info, (info2, c) ->
        {
            GameActions.Top.IncreaseScaling(c, Affinity.Green, magicNumber);
            c.flash();
        });
    }
}