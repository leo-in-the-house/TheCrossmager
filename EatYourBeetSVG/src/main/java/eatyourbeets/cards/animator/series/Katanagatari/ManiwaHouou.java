package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnAffinitySealedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ManiwaHouou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ManiwaHouou.class)
            .SetPower(1, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public ManiwaHouou()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Violet(1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new ManiwaHououPower(p, magicNumber));
    }

    public static class ManiwaHououPower extends AnimatorPower implements OnAffinitySealedSubscriber
    {
        public ManiwaHououPower(AbstractCreature owner, int amount)
        {
            super(owner, ManiwaHouou.DATA);

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
            for (EYBCardAffinity affinity : card.affinities.List) {
                PowerHelper bonusPower = affinity.type.GetPower().GetThresholdBonusPower();
                int affinityAmount = affinity.level;

                if (bonusPower != null && affinityAmount > 0) {
                    GameActions.Bottom.ApplyPower(bonusPower.Create(player, player, affinityAmount));
                }
            }

            this.amount -= 1;
            if (this.amount <= 0) {
                RemovePower();
            }
        }
    }
}