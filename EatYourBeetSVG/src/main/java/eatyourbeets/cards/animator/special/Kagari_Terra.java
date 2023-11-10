package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.orbs.animator.Earth;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kagari_Terra extends AnimatorCard {
    public static final EYBCardData DATA = Register(Kagari_Terra.class)
            .SetPower(0, CardRarity.SPECIAL)
            .SetSeries(CardSeries.Rewrite);

    public Kagari_Terra() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_Green(1);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new Kagari_TerraPower(p, magicNumber));
    }

    public static class Kagari_TerraPower extends AnimatorPower implements OnStanceChangedSubscriber {
        public Kagari_TerraPower(AbstractCreature owner, int amount) {
            super(owner, Kagari_Terra.DATA);
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
            GameActions.Bottom.ChannelOrbs(Earth::new, amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}