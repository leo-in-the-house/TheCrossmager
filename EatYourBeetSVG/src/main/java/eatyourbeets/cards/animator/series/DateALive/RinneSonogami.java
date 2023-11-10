package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RinneSonogami extends AnimatorCard {
    public static final EYBCardData DATA = Register(RinneSonogami.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public RinneSonogami() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Pink(1);
        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new RinneSonogamiPower(p, 1));
    }

    public static class RinneSonogamiPower extends AnimatorClickablePower {
        public RinneSonogamiPower(AbstractCreature owner, int amount) {
            super(owner, RinneSonogami.DATA, PowerTriggerConditionType.Energy, 1);

            triggerCondition.SetUses(1, true, true);

            Initialize(amount);
        }

        @Override
        public void OnUse(AbstractMonster m) {
            super.OnUse(m);

            GameActions.Bottom.GainBlock(CombatStats.BlockGainedLastTurn * amount);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, amount, CombatStats.BlockGainedLastTurn * amount);
        }
    }
}