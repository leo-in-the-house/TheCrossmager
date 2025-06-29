package eatyourbeets.cards.animatorClassic.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animatorClassic.special.Melzalgald_B;
import eatyourbeets.cards.animatorClassic.special.Melzalgald_G;
import eatyourbeets.cards.animatorClassic.special.Melzalgald_R;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Melzalgald extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Melzalgald.class).SetSeriesFromClassPackage()
            .SetAttack(3, CardRarity.UNCOMMON)
            .PostInitialize(data ->
            {
                data.AddPreview(new Melzalgald_R(), true);
                data.AddPreview(new Melzalgald_B(), true);
                data.AddPreview(new Melzalgald_G(), true);
            });

    public Melzalgald()
    {
        super(DATA);

        Initialize(21, 0);
        SetScaling(2, 2, 2);

        SetExhaust(true);

        SetShapeshifter();
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);

        GameActions.Bottom.MakeCardInHand(new Melzalgald_R()).SetUpgrade(upgraded, false).AddCallback(GameUtilities::Retain);
        GameActions.Bottom.MakeCardInHand(new Melzalgald_B()).SetUpgrade(upgraded, false).AddCallback(GameUtilities::Retain);
        GameActions.Bottom.MakeCardInHand(new Melzalgald_G()).SetUpgrade(upgraded, false).AddCallback(GameUtilities::Retain);
    }
}