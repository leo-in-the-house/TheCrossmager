package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TK extends AnimatorCard {
    public static final EYBCardData DATA = Register(TK.class).SetAttack(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public TK() {
        super(DATA);

        Initialize(6, 0, 2);
        SetUpgrade(4, 0, 0);

        SetAffinity_Green(1, 0, 2);

        SetAffinityRequirement(Affinity.Green, 4);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

        int numEtherealInExhaust = 0;

        for (AbstractCard card : player.exhaustPile.group) {
            if (card.isEthereal) {
                numEtherealInExhaust++;
            }
        }

        if (numEtherealInExhaust > 2) {
            GameActions.Bottom.GainBlur(1);
        }

        if (CheckSpecialCondition(false)) {
            for (AbstractCard card : player.drawPile.group) {
                if (card.type == CardType.ATTACK && card.costForTurn >= 1) {
                    card.isEthereal = true;
                    CostModifiers.For(card).Add(-1);
                }
            }
        }
    }
}