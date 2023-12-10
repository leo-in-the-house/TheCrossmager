package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class MariaArusu extends AnimatorCard {
    public static final EYBCardData DATA = Register(MariaArusu.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public MariaArusu() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Teal(1);
        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new MariaArusuPower(p, 1));
    }

    public static class MariaArusuPower extends AnimatorPower {
        public MariaArusuPower(AbstractCreature owner, int amount) {
            super(owner, MariaArusu.DATA);
            Initialize(amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            RandomizedList<AbstractCard> cardPossibilities = new RandomizedList<>();

            int highestBlock = 0;

            for (AbstractCard card : player.discardPile.group) {
                int cardBlock = card.block;
                if (cardBlock < player.currentBlock && cardBlock >= highestBlock) {
                    if (cardBlock > highestBlock) {
                        highestBlock = cardBlock;
                        cardPossibilities.Clear();
                    }

                    if (!card.isEthereal) {
                        cardPossibilities.Add(card.makeCopy());
                    }
                }
            }

            if (cardPossibilities.Size() > 0) {
                for (int i=0; i<amount; i++) {
                    GameActions.Bottom.MakeCardInHand(cardPossibilities.Retrieve(rng, false).makeCopy())
                            .AddCallback(GameUtilities::Retain);
                }
            }
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}