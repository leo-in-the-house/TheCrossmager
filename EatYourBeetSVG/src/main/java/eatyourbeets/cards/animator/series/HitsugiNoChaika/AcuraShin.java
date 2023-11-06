package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnAfterCardDiscardedSubscriber;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.powers.replacement.TemporaryEnvenomPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AcuraShin extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AcuraShin.class)
            .SetPower(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public AcuraShin()
    {
        super(DATA);

        Initialize(0,0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Green(1);
        SetAffinity_Black(1);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new AcuraShinPower(p, magicNumber));
    }

    public static class AcuraShinPower extends AnimatorClickablePower implements OnAfterCardDiscardedSubscriber
    {
        public AcuraShinPower(AbstractCreature owner, int amount)
        {
            super(owner, AcuraShin.DATA, PowerTriggerConditionType.None, 0);

            triggerCondition.SetUses(2, true, true);

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
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            GameActions.Top.Cycle(name, 1).DrawInstantly(true);
        }

        @Override
        public void OnAfterCardDiscarded() {
            GameActions.Bottom.StackPower(new TemporaryEnvenomPower(owner, amount));
        }
    }
}