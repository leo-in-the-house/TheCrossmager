package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.ArtfulSacrifice;
import eatyourbeets.cards.animator.special.ShanghaiDoll;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnAttackSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AliceMargatroid extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AliceMargatroid.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new ShanghaiDoll(), true);
                data.AddPreview(new ArtfulSacrifice(), true);
            });

    public AliceMargatroid()
    {
        super(DATA);

        Initialize(0, 0, 4);
        SetUpgrade(0, 0, 0);

        SetAffinity_Teal(1);
        SetAffinity_Brown(1);

        SetFading(true);

        SetAffinityRequirement(Affinity.Teal, 3);
        SetAffinityRequirement(Affinity.Brown, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new AliceMargatroidPower(player, magicNumber, upgraded));

        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.MakeCardInHand(new ArtfulSacrifice())
                    .SetUpgrade(upgraded, true);
        }
    }

    public static class AliceMargatroidPower extends AnimatorPower implements OnAttackSubscriber {

        private boolean upgraded;

        public AliceMargatroidPower(AbstractCreature owner, int amount, boolean upgraded) {
            super(owner, AliceMargatroid.DATA);

            this.upgraded = upgraded;

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
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            SetEnabled(false);
            RemovePower();
            flash();
        }

        @Override
        public void OnAttack(DamageInfo info, int damageAmount, AbstractCreature target)
        {
            if (info.type == DamageInfo.DamageType.NORMAL && GameUtilities.IsMonster(target))
            {
                GameActions.Bottom.MakeCardInHand(new ShanghaiDoll())
                        .SetUpgrade(upgraded, true);
                this.flash();
                ReducePower(1);
            }
        }
    }
}

