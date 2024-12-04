package eatyourbeets.cards.animator.series.FullmetalAlchemist;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnAffinitySealedSubscriber;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Envy extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Envy.class)
            .SetPower(1, CardRarity.RARE)
            .SetSeriesFromClassPackage();
    public static final int TEMP_HP_ENERGY_COST = 2;

    public Envy()
    {
        super(DATA);

        Initialize(0, 0, TEMP_HP_ENERGY_COST);
        SetCostUpgrade(-1);

        SetAffinity_Pink(1);
        SetAffinity_Violet(1);

        SetDelayed(true);
        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new EnvyPower(p, 1));
    }

    public static class EnvyPower extends AnimatorClickablePower implements OnAffinitySealedSubscriber
    {
        private int vitality;

        public EnvyPower(AbstractPlayer owner, int amount)
        {
            super(owner, Envy.DATA, PowerTriggerConditionType.Energy, Envy.TEMP_HP_ENERGY_COST);

            triggerCondition.SetUses(1, true, false);
            canBeZero = true;

            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAffinitySealed.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAffinitySealed.Unsubscribe(this);
        }

        @Override
        public void OnAffinitySealed(EYBCard card, boolean manual)
        {
            if (amount > 0)
            {
                GameActions.Bottom.ChannelOrbs(Dark::new, amount);
                flashWithoutSound();
            }
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, vitality, amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            GameActions.Bottom.SealAffinities(player.discardPile, 1, false);
        }
    }
}