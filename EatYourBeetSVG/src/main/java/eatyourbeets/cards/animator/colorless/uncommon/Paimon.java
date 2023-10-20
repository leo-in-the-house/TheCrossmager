package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Paimon extends AnimatorCard {
    public static final EYBCardData DATA = Register(Paimon.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public Paimon() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Green(1);

        SetExhaust(true);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        int numGenshinImpactCards = 0;

        for (AbstractCard card : player.masterDeck.group) {
            if (card instanceof AnimatorCard) {
                AnimatorCard animatorCard = (AnimatorCard) card;

                if (GameUtilities.IsSeries(card, CardSeries.GenshinImpact)) {
                    numGenshinImpactCards++;
                }
            }
        }

        GameActions.Bottom.Heal(numGenshinImpactCards);
    }
}