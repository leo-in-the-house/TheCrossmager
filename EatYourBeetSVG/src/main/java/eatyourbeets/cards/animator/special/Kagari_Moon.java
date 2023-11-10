package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kagari_Moon extends AnimatorCard {
    public static final EYBCardData DATA = Register(Kagari_Moon.class)
            .SetPower(0, CardRarity.SPECIAL)
            .SetSeries(CardSeries.Rewrite);

    public Kagari_Moon() {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 0);

        SetAffinity_Green(1);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new Kagari_MoonPower(p, magicNumber));
    }

    public static class Kagari_MoonPower extends AnimatorPower implements OnStanceChangedSubscriber {
        public Kagari_MoonPower(AbstractCreature owner, int amount) {
            super(owner, Kagari_Moon.DATA);
            Initialize(amount);
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
            for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                GameActions.Bottom.ReduceStrength(player, enemy, amount, true);
            }
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}