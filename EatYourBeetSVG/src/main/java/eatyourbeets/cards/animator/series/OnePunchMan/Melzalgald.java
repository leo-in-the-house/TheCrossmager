package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Melzalgald_B;
import eatyourbeets.cards.animator.special.Melzalgald_G;
import eatyourbeets.cards.animator.special.Melzalgald_R;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Melzalgald extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Melzalgald.class)
            .SetAttack(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Melzalgald_R(), true);
                data.AddPreview(new Melzalgald_B(), true);
                data.AddPreview(new Melzalgald_G(), true);
            });

    public Melzalgald()
    {
        super(DATA);

        Initialize(9, 0, 0);
        SetUpgrade(5, 0, 0);

        SetAffinity_Red(1);
        SetAffinity_Green(1);
        SetAffinity_Blue(1);
        SetScaling(Affinity.Star, 1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);

        GameActions.Bottom.MakeCardInHand(new Melzalgald_R()).SetUpgrade(upgraded, false);
        GameActions.Bottom.MakeCardInHand(new Melzalgald_B()).SetUpgrade(upgraded, false);
        GameActions.Bottom.MakeCardInHand(new Melzalgald_G()).SetUpgrade(upgraded, false);
    }
}