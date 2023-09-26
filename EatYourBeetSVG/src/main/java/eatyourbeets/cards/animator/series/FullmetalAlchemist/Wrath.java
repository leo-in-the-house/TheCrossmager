package eatyourbeets.cards.animator.series.FullmetalAlchemist;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.animator.tokens.AffinityToken;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Wrath extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Wrath.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(AffinityToken.GetCard(Affinity.Red), true));

    public Wrath()
    {
        super(DATA);

        Initialize(0, 20, 6);
        SetUpgrade(0, 8, 2);

        SetAffinity_Red(2, 0, 2);
        SetAffinity_White(1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
        GameActions.Bottom.StackPower(new WrathPower(player, magicNumber));
    }

    public static class WrathPower extends AnimatorPower implements OnStanceChangedSubscriber
    {
        public WrathPower(AbstractPlayer owner, int amount)
        {
            super(owner, Wrath.DATA);

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
        protected void onAmountChanged(int previousAmount, int difference)
        {
            GameActions.Top.StackPower(new FocusPower(owner, difference));

            super.onAmountChanged(previousAmount, difference);
        }

        @Override
        public void OnStanceChanged(AbstractStance oldStance, AbstractStance newStance) {
            RemovePower();
        }
    }
}