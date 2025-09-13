package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.interfaces.subscribers.OnChannelOrbSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TowaHerschel extends AnimatorCard {
    public static final EYBCardData DATA = Register(TowaHerschel.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public TowaHerschel() {
        super(DATA);

        Initialize(0, 0, 100);
        SetUpgrade(0, 0, 100);

        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new TowaHerschelPower(p, magicNumber));
    }

    public static class TowaHerschelPower extends AnimatorPower implements OnChannelOrbSubscriber
    {
        public TowaHerschelPower(AbstractPlayer owner, int amount)
        {
            super(owner, TowaHerschel.DATA);

            this.amount = amount;

            updateDescription();
        }


        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onChannelOrb.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onChannelOrb.Unsubscribe(this);
        }

        @Override
        public void atStartOfTurn()
        {
            RemovePower();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void OnChannelOrb(AbstractOrb orb) {
            if (Lightning.ORB_ID.equals(orb.ID)) {
                flash();
                int energyToGain = EnergyPanel.totalCount * (amount / 100);
                GameActions.Bottom.GainEnergy(energyToGain);
                RemovePower();
            }
        }

        public int on(DamageInfo info, int damageAmount)
        {
            if (this.amount <= 0)
            {
                return damageAmount;
            }

            this.amount--;

            GameActions.Top.ChangeStance(WrathStance.STANCE_ID);

            return damageAmount;
        }
    }
}