package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Clownpiece extends AnimatorCard {
    public static final EYBCardData DATA = Register(Clownpiece.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public Clownpiece() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetExhaust(true);

        SetAffinity_Black(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        AbstractCard card = FindTopMatchingCard();

        if (card != null) {
            int damageAmount = card.costForTurn;
            GameActions.Bottom.PlayCard(card, player.drawPile, m);

            if (damageAmount > 0) {
                GameActions.Bottom.TakeDamageAtEndOfTurn(damageAmount);
            }
        }
    }

    private AbstractCard FindTopMatchingCard()
    {
        AbstractCard bestCard = null;

        for (AbstractCard c : player.drawPile.group)
        {
            if (GameUtilities.HasAttackOrBlockMultiplier(c))
            {
                bestCard = c;
            }
        }
        return bestCard;
    }
}