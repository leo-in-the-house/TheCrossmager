package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.MadokaMagica.KyokoSakura;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KyokoSakura_Ophelia extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KyokoSakura_Ophelia.class)
            .SetSkill(3, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(KyokoSakura.DATA.Series);

    public KyokoSakura_Ophelia()
    {
        super(DATA);

        Initialize(0, 25, 3, 0);
        SetUpgrade(0, 12, 0);

        SetAffinity_Red(2, 0, 1);
        SetAffinity_Black(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.PurgeFromPile(name, magicNumber, player.exhaustPile)
            .SetFilter(card -> card.type == CardType.CURSE)
            .SetOptions(true, true)
            .AddCallback(cards -> {
                int numCardsPurged = cards.size();

                if (numCardsPurged > 0) {
                    GameActions.Top.Draw(numCardsPurged)
                    .AddCallback(cardsDrawn -> {
                        for (AbstractCard cardDrawn : cardsDrawn) {
                            CostModifiers.For(cardDrawn).Add(-1);
                        }
                    });
                }
            });
    }
}