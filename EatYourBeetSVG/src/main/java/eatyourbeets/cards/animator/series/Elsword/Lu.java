package eatyourbeets.cards.animator.series.Elsword;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.GR;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Lu extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Lu.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Piercing)
            
            .SetSeriesFromClassPackage()
            .ModifyRewards((data, rewards) ->
            {
                if (Ciel.DATA.GetTotalCopies(player.masterDeck) > 0)
                {
                    GR.Common.Dungeon.TryReplaceFirstCardReward(rewards, 0.075f, true, data);
                }
            });

    public Lu()
    {
        super(DATA);

        Initialize(14, 0, 3);
        SetUpgrade(6, 0, 0);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Red(1, 0, 1);
        SetAffinity_Black(1);
    }

    @Override
    protected void OnUpgrade()
    {
        SetAttackTarget(EYBCardTarget.ALL);
        SetMultiDamage(true);
        upgradedDamage = true;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        if (isMultiDamage)
        {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE)
            .SetDamageEffect((enemy, __) -> GameEffects.List.Add(VFX.Claw(enemy.hb, Color.VIOLET, Color.WHITE)));
        }
        else
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE)
            .SetDamageEffect(enemy -> GameEffects.List.Add(VFX.Claw(enemy.hb, Color.VIOLET, Color.WHITE)).duration);
        }

        if (damage >= 20)
        {
            GameActions.Bottom.Add(new ShakeScreenAction(0.8f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.MED));
        }

        GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID)
                .AddCallback(stance ->
                {
                    if (stance != null)
                    {
                        GameActions.Bottom.Flash(this);
                        GameActions.Bottom.TakeDamageAtEndOfTurn(magicNumber, AttackEffects.CLAW);
                        GameActions.Bottom.StackPower(new LuPower(player, 1));
                    }
                });
    }

    public static class LuPower extends AnimatorPower implements OnStanceChangedSubscriber
    {
        public LuPower(AbstractCreature owner, int amount)
        {
            super(owner, Lu.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onStanceChanged.Subscribe(this);
            CombatStats.setEnableConsumingStatsWithScalingCard(false);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onStanceChanged.Unsubscribe(this);
            CombatStats.setEnableConsumingStatsWithScalingCard(true);
        }

        @Override
        public void OnStanceChanged(AbstractStance oldStance, AbstractStance newStance) {
            RemovePower();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}