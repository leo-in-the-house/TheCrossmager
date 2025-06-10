package eatyourbeets.cards.animator.colorless.uncommon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.*;

public class Madotsuki extends AnimatorCard {
    public static final EYBCardData DATA = Register(Madotsuki.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.YumeNikki);

    public Madotsuki() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Pink(1);

        SetEthereal(true);
        SetExhaust(true);
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        RandomizedList<AbstractCard> cardsInDrawPile = new RandomizedList<>();

        for (AbstractCard card : player.drawPile.group) {
            if (!GameUtilities.IsHindrance(card)) {
                cardsInDrawPile.Add(card);
            }
        }

        final WeightedList<AbstractCard> possibleCards = GameUtilities.GetCardsInCombatWeighted(GenericCondition.FromT1(c -> (c.rarity == CardRarity.COMMON || c.rarity == CardRarity.UNCOMMON || c.rarity == CardRarity.RARE) && c.isEthereal));

        int numTransform = cardsInDrawPile.Size() / 3;

        if (numTransform > 0) {
            GameActions.Bottom.VFX(new BorderLongFlashEffect(Color.PURPLE));

            for (int i=0; i<numTransform; i++) {
                AbstractCard card = cardsInDrawPile.Retrieve(rng);
                AbstractCard targetCard = possibleCards.Retrieve(rng, true);

                if (card != null && targetCard != null) {
                    boolean cardUpgraded = card.upgraded;

                    GameActions.Bottom.ReplaceCard(card.uuid, targetCard)
                            .SetUpgrade(cardUpgraded);
                }
            }
        }

    }
}