package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;


public class Nikolay extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Nikolay.class)
            .SetAttack(3, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Nikolay()
    {
        super(DATA);

        Initialize(17, 0, 1);
        SetUpgrade(8, 0, 1);

        SetAffinity_Red(2, 0, 0);
        SetAffinity_Yellow(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HEAVY);

        GameActions.Bottom.DiscardFromHand(name, 1, false)
           .SetOptions(true, true, true)
           .AddCallback(cards -> {
               if (cards.size() > 0) {
                   GameActions.Top.ModifyAllCopies(cardID)
                           .AddCallback(c ->
                           {
                               CostModifiers.For(c).Add(-magicNumber);
                           });
               }
           });
    }

}