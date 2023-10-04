package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Bennett extends AnimatorCard {
    public static final EYBCardData DATA = Register(Bennett.class)
            .SetAttack(1, CardRarity.UNCOMMON)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public Bennett() {
        super(DATA);

        Initialize(15, 0, 5, 5);
        SetUpgrade(4, 0, 0);

        SetAffinity_Red(1);
        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        int maxHPPercentage = (player.currentHealth * 100) / player.maxHealth;

        if (maxHPPercentage > 30) {
            GameActions.Bottom.TakeDamageAtEndOfTurn(secondaryValue);
        }
        else {
            GameActions.Bottom.GainVigor(magicNumber);
        }
    }
}