package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Geryuganshoop extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Geryuganshoop.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Geryuganshoop()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Pink(2);
        SetAffinity_Yellow(2);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new GeryuganshoopPower(player, magicNumber));
    }

    public static class GeryuganshoopPower extends AnimatorPower implements OnStanceChangedSubscriber
    {
        public GeryuganshoopPower(AbstractCreature owner, int amount)
        {
            super(owner, Geryuganshoop.DATA);

            this.amount = amount;

            updateDescription();
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
            if (newStance.ID.equals(WrathStance.STANCE_ID)) {
                GameActions.Bottom.GainRed(amount);
            }
            else if (newStance.ID.equals(TranceStance.STANCE_ID)) {
                GameActions.Bottom.GainGreen(amount);
            }
            else if (newStance.ID.equals(MagicStance.STANCE_ID)) {
                GameActions.Bottom.GainBlue(amount);
            }
            else if (newStance.ID.equals(CalmStance.STANCE_ID)) {
                GameActions.Bottom.GainPink(amount);
                GameActions.Bottom.GainYellow(amount);
            }
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}