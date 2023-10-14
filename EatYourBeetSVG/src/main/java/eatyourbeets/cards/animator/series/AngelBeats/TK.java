package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TK extends AnimatorCard {
    public static final EYBCardData DATA = Register(TK.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage();

    public TK() {
        super(DATA);

        Initialize(10, 0, 2);
        SetUpgrade(8, 0, 0);

        SetAffinity_Green(2, 0, 2);

        SetAffinityRequirement(Affinity.Green, 4);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);

        int numEtherealInExhaust = 0;

        for (AbstractCard card : player.exhaustPile.group) {
            if (card.isEthereal) {
                numEtherealInExhaust++;
            }
        }

        if (numEtherealInExhaust >= 2) {
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