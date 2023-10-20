package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.MiyukiIrie;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class ShioriSekine extends AnimatorCard implements OnAddToDeckListener {
    public static final EYBCardData DATA = Register(ShioriSekine.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new MiyukiIrie(), true);
    }

    public ShioriSekine() {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 2);

        SetAffinity_Yellow(2);

        SetAutoplayed(true);
    }

    @Override
    public boolean OnAddToDeck()
    {
        GameEffects.TopLevelQueue.ShowAndObtain(new MiyukiIrie());

        return true;
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
                    GameActions.Top.GainBlock(amount);
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