package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.RozenMaiden.Shirosaki;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.interfaces.subscribers.OnCardRetainedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shirosaki_Laplace extends AnimatorCard {
    public static final EYBCardData DATA = Register(Shirosaki_Laplace.class)
            .SetPower(1, CardRarity.SPECIAL)
            .SetSeries(Shirosaki.DATA.Series);

    public Shirosaki_Laplace() {
        super(DATA);

        Initialize(0, 8, 5);
        SetUpgrade(0, 3, 0);

        SetAffinity_Pink(1);
        SetAffinity_Black(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.StackPower(new Shirosaki_LaplacePower(p, magicNumber));
    }

    public static class Shirosaki_LaplacePower extends AnimatorPower implements OnCardRetainedSubscriber {
        public Shirosaki_LaplacePower(AbstractCreature owner, int amount) {
            super(owner, Shirosaki_Laplace.DATA);
            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onCardRetained.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onCardRetained.Unsubscribe(this);
        }

        @Override
        public void OnCardRetained(AbstractCard card) {
            GameActions.Bottom.GainBlock(amount);
            CostModifiers.For(card).Add(-1);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}