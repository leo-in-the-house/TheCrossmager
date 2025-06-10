package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class GoldiaDieHeilige extends AnimatorCard {
    public static final EYBCardData DATA = Register(GoldiaDieHeilige.class)
            .SetPower(2, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.PocketMirror);

    public GoldiaDieHeilige() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Yellow(1);
        SetAffinity_Pink(1);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new GoldiaDieHeiligePower(p, magicNumber));
    }

    public static class GoldiaDieHeiligePower extends AnimatorPower {
        public GoldiaDieHeiligePower(AbstractCreature owner, int amount) {
            super(owner, GoldiaDieHeilige.DATA);
            Initialize(amount);
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            int numCardsToPickFrom = 2;

            if (amount > 0) {
                RandomizedList<AbstractCard> possibleCards = new RandomizedList<>();

                for (AbstractCard card : player.masterDeck.group) {
                    if (GameUtilities.HasAffinity(card, Affinity.Yellow)) {
                        AbstractCard copy = card.makeCopy();

                        if (card.upgraded) {
                            copy.upgrade();
                        }

                        possibleCards.Add(copy);
                    }
                }

                if (possibleCards.Size() >= numCardsToPickFrom) {
                    CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                    for (int i=0; i<numCardsToPickFrom; i++) {
                        group.addToBottom(possibleCards.Retrieve(rng));
                    }

                    GameActions.Bottom.SelectFromPile(name, 1, group)
                            .SetOptions(false, false)
                            .AddCallback(cards ->
                            {
                                for (AbstractCard card : cards) {
                                    GameActions.Top.MakeCardInHand(card)
                                            .Repeat(amount);
                                }
                            });
                }
            }
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}