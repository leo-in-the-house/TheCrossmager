package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnAfterCardDiscardedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class LeonardoStola extends AnimatorCard {
    public static final EYBCardData DATA = Register(LeonardoStola.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public LeonardoStola() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Green(1);
        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new LeonardoStolaPower(p, magicNumber));
    }

    public static class LeonardoStolaPower extends AnimatorPower implements OnAfterCardDiscardedSubscriber {
        public LeonardoStolaPower(AbstractCreature owner, int amount) {
            super(owner, LeonardoStola.DATA);
            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAfterCardDiscarded.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAfterCardDiscarded.Unsubscribe(this);
        }

        @Override
        public void OnAfterCardDiscarded() {
            if (this.amount > 0) {
                GameActions.Bottom.Draw(1);
                this.amount -= 1;
                updateDescription();
                flash();
            }
        }

        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            ResetAmount();
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}