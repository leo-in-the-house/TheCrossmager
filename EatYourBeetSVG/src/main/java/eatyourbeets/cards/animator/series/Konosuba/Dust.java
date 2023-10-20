package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Dust extends AnimatorCard {
    public static final EYBCardData DATA = Register(Dust.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Dust() {
        super(DATA);

        Initialize(0, 4, 0);
        SetUpgrade(0, 1, 0);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ExhaustFromHand(name, 1, false)
        .SetFilter(card -> card.rarity == CardRarity.UNCOMMON)
        .SetOptions(true, true, false)
        .AddCallback(cards ->
        {
            if (cards.size() > 0)
            {
                AbstractCard cardToCreate = AbstractDungeon.getCardFromPool(CardRarity.UNCOMMON, CardType.POWER, true);

                if (cardToCreate != null) {
                    GameActions.Top.MakeCardInDrawPile(cardToCreate.makeCopy())
                            .SetUpgrade(upgraded, true);
                }
            }
        });
    }
}