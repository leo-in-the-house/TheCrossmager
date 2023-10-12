package eatyourbeets.cards.animatorClassic.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animatorClassic.special.ThrowingKnife;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.replacement.TemporaryEnvenomPower;
import eatyourbeets.utilities.GameActions;

public class AcuraAkari extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(AcuraAkari.class).SetSeriesFromClassPackage()
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None);
    static
    {
        for (ThrowingKnife knife : ThrowingKnife.GetAllCards())
        {
            DATA.AddPreview(knife, false);
        }
    }

    public AcuraAkari()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DiscardFromHand(name, magicNumber, false)
        .SetOptions(true, true, true)
        .AddCallback(cards -> {
            if (cards.size() > 0) {
                GameActions.Bottom.CreateThrowingKnives(cards.size());
            }
         });

        if (info.IsSynergizing && CombatStats.TryActivateSemiLimited(cardID))
        {
            GameActions.Bottom.StackPower(new TemporaryEnvenomPower(p, secondaryValue));
        }
    }
}