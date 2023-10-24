package eatyourbeets.cards.animator.series.GATE;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.MoltSolAugustus_ImperialArchers;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.SFX;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MoltSolAugustus extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MoltSolAugustus.class)
            .SetPower(1, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new MoltSolAugustus_ImperialArchers(), true));
    private static final int ENERGY_COST = 1;

    public MoltSolAugustus()
    {
        super(DATA);

        Initialize(0, 0);

        SetAffinity_Brown(2);
        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new MoltSolAugustusPower(p, 1, upgraded));
    }

    public static class MoltSolAugustusPower extends AnimatorClickablePower
    {
        private boolean upgrade;

        public MoltSolAugustusPower(AbstractCreature owner, int amount, boolean upgrade)
        {
            super(owner, MoltSolAugustus.DATA, PowerTriggerConditionType.Energy, MoltSolAugustus.ENERGY_COST);

            triggerCondition.SetUses(3, true, true);
            canBeZero = true;
            this.upgrade = upgrade;

            Initialize(amount);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(upgrade ? 1 : 0, amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            GameActions.Bottom.SFX(SFX.RELIC_DROP_FLAT);
            GameActions.Bottom.MakeCardInDrawPile(new MoltSolAugustus_ImperialArchers())
                    .SetUpgrade(upgrade, true)
                    .SetDuration(0.1f, false);
            GameActions.Bottom.SFX(SFX.ANIMATOR_ARROW);
        }
    }
}