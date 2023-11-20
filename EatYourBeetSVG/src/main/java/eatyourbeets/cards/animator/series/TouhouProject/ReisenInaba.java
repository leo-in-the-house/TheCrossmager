package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.interfaces.subscribers.OnAttackSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class ReisenInaba extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ReisenInaba.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    public ReisenInaba()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new ReisenInabaPower(player, magicNumber));
    }

    public static class ReisenInabaPower extends AnimatorPower implements OnAttackSubscriber {
        public ReisenInabaPower(AbstractCreature owner, int amount) {
            super(owner, ReisenInaba.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAttack.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAttack.Unsubscribe(this);
        }


        @Override
        public void OnAttack(DamageInfo info, int damageAmount, AbstractCreature target)
        {
            if (info.type == DamageInfo.DamageType.NORMAL && GameUtilities.IsMonster(target))
            {
                GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(target), amount);
                this.flash();
            }
        }
    }
}