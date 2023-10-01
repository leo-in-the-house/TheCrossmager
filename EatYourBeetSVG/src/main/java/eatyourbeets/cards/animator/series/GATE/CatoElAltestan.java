package eatyourbeets.cards.animator.series.GATE;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class CatoElAltestan extends AnimatorCard
{
    public static final EYBCardData DATA = Register(CatoElAltestan.class)
            .SetSkill(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public CatoElAltestan()
    {
        super(DATA);

        Initialize(0, 4, 1);
        SetUpgrade(0, 6, 2);

        SetAffinity_Blue(2);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.FetchFromPile(name, magicNumber, player.drawPile)
        .SetOptions(false, true)
        .SetFilter(card -> card.type == CardType.ATTACK)
        .AddCallback(cards ->
        {
            for (AbstractCard c : cards)
            {
                GameActions.Top.IncreaseScaling(c, Affinity.Blue, magicNumber);
                GameActions.Top.Callback(() -> {
                    if (c instanceof AnimatorCard) {
                        ((AnimatorCard) c).AddScaling(Affinity.Blue, 1);
                        ((AnimatorCard) c).SetAttackType(EYBAttackType.Elemental);
                    }
                });
            }
        });
    }
}