package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MitsuKusabue extends AnimatorCard {
    public static final EYBCardData DATA = Register(MitsuKusabue.class)
            .SetPower(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public MitsuKusabue() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetEthereal(true);

        SetAffinity_Pink(1);
        SetAffinity_Yellow(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new MitsuKusabuePower(p, 1));
    }

    public static class MitsuKusabuePower extends AnimatorPower {
        public MitsuKusabuePower(AbstractCreature owner, int amount) {
            super(owner, MitsuKusabue.DATA);
            Initialize(amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            GameActions.Bottom.SelectFromHand(name, 1, false)
                .SetOptions(true, false, false)
                .SetMessage(RetainCardsAction.TEXT[0])
                .AddCallback(cards -> {
                   for (AbstractCard card : cards) {
                       GameUtilities.Retain(card);
                       if (card instanceof EYBCard) {
                           for (EYBCardAffinity affinity : ((EYBCard) card).affinities.List) {
                               if (affinity.level > 0 && affinity.type != Affinity.Sealed) {
                                   GameActions.Top.IncreaseScaling(card, affinity.type, affinity.level);
                               }
                           }
                       }
                   }
                });
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}