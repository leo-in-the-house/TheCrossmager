package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.animator.special.Special_Miracle;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.stances.DivinityStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SakuraKashima extends AnimatorCard {
    public static final EYBCardData DATA = Register(SakuraKashima.class)
            .SetPower(3, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Rewrite);

    public SakuraKashima() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Green(2);
        SetAffinity_Blue(2);
        SetAffinity_Violet(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new SakuraKashimaPower(p, 1));
    }

    public static class SakuraKashimaPower extends AnimatorClickablePower implements OnStanceChangedSubscriber {
        public SakuraKashimaPower(AbstractCreature owner, int amount) {
            super(owner, SakuraKashima.DATA, PowerTriggerConditionType.Exhaust, 1);

            triggerCondition.SetUses(1, true, true);

            Initialize(amount);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onStanceChanged.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onStanceChanged.Unsubscribe(this);
        }

        @Override
        public void OnStanceChanged(AbstractStance oldStance, AbstractStance newStance) {
            GameActions.Bottom.MakeCardInHand(new Special_Miracle());
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            GameActions.Bottom.ChangeStance(DivinityStance.STANCE_ID);
        }
    }
}