package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ib_Mary extends AnimatorCard {
    public static final EYBCardData DATA = Register(Ib_Mary.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.Ib);

    public Ib_Mary() {
        super(DATA);

        Initialize(0, 6, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_Yellow(1, 0, 2);
        SetScaling(Affinity.Red, 2);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        int numCardsInMasterDeck = 0;

        for (AbstractCard card : p.masterDeck.group) {
            if (card.type == CardType.CURSE) {
                numCardsInMasterDeck++;
            }
            else if (GameUtilities.HasAffinity(card, Affinity.Yellow)) {
                numCardsInMasterDeck++;
            }
        }

        if (numCardsInMasterDeck > 0) {
            GameActions.Bottom.GainTemporaryHP(numCardsInMasterDeck);
            GameActions.Bottom.GainYellow(numCardsInMasterDeck);
        }
    }
}