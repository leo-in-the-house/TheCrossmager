package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnCardCreatedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Nelke extends AnimatorCard {
    public static final EYBCardData DATA = Register(Nelke.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Nelke() {
        super(DATA);

        Initialize(0, 0, 2, 1);
        SetUpgrade(0, 0, 2, 1);

        SetAffinity_Yellow(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new NelkePower(p, magicNumber, secondaryValue));
    }

    public static class NelkePower extends AnimatorPower implements OnCardCreatedSubscriber {

        int tempHPAmount = 0;
        public NelkePower(AbstractCreature owner, int blockAmount, int tempHPAmount) {
            super(owner, Nelke.DATA);
            Initialize(blockAmount);
            this.tempHPAmount = tempHPAmount;
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onCardCreated.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onCardCreated.Unsubscribe(this);
        }

        @Override
        public void OnCardCreated(AbstractCard card, boolean startOfBattle) {
            if (enabled && !startOfBattle) {
                GameActions.Bottom.GainBlock(amount);
                GameActions.Bottom.GainTemporaryHP(tempHPAmount);
            }
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount, tempHPAmount);
        }
    }
}