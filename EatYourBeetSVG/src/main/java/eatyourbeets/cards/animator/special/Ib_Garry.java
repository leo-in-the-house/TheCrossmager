package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ib_Garry extends AnimatorCard {
    public static final EYBCardData DATA = Register(Ib_Garry.class)
            .SetAttack(0, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeries(CardSeries.Ib);

    public Ib_Garry() {
        super(DATA);

        Initialize(6, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Blue(1, 0, 2);
        SetScaling(Affinity.Red, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, GR.Enums.AttackEffect.PUNCH);

        int numCardsInMasterDeck = 0;

        for (AbstractCard card : p.masterDeck.group) {
            if (card.type == CardType.CURSE) {
                numCardsInMasterDeck++;
            }
            else if (GameUtilities.HasAffinity(card, Affinity.Blue)) {
                numCardsInMasterDeck++;
            }
        }

        if (numCardsInMasterDeck > 0) {
            GameActions.Bottom.GainTemporaryHP(numCardsInMasterDeck);
            GameActions.Bottom.GainBlue(numCardsInMasterDeck);
        }
    }
}