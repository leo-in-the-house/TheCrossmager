package eatyourbeets.cards.animator.series.AngelBeats;

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

public class ShioriSekine extends AnimatorCard {
    public static final EYBCardData DATA = Register(ShioriSekine.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public ShioriSekine() {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 2);

        SetAffinity_Yellow(2);

        SetAutoplayed(true);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new ShioriSekinePower(p, 5));
    }

    public static class ShioriSekinePower extends AnimatorPower {
        public ShioriSekinePower(AbstractCreature owner, int amount) {
            super(owner, ShioriSekine.DATA);
            Initialize(amount);
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            GameActions.Bottom.GainBlock(amount);

            GameActions.Bottom.ExhaustFromHand(name, 1, true)
            .SetFilter(card -> card.isEthereal)
            .AddCallback(cards -> {
                for (AbstractCard card : cards)
                {
                    GameActions.Top.GainTemporaryHP(amount);
                    GameActions.Top.SealAffinities(card, false);
                }
            });
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}