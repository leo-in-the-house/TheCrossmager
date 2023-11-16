package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnAfterCardDrawnSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Gabiru extends AnimatorCard {
    public static final EYBCardData DATA = Register(Gabiru.class)
            .SetPower(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Gabiru() {
        super(DATA);

        Initialize(0, 4, 5);
        SetCostUpgrade(-1);

        SetAffinity_Teal(1, 0, 1);
        SetAffinity_Pink(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new GabiruPower(p, magicNumber));
    }

    public static class GabiruPower extends AnimatorPower implements OnAfterCardDrawnSubscriber {
        public GabiruPower(AbstractCreature owner, int amount) {
            super(owner, Gabiru.DATA);
            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAfterCardDrawn.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAfterCardDrawn.Unsubscribe(this);
        }

        @Override
        public void OnAfterCardDrawn(AbstractCard card)
        {
            if (CombatStats.CardsDrawnThisTurn() == 1 && GameUtilities.IsHighCost(card)) {
                GameActions.Bottom.GainEnergy(amount);
            }
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}