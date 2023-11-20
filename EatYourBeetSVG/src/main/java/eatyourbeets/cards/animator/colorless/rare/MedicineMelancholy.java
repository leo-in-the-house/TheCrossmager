package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnAttackSubscriber;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class MedicineMelancholy extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MedicineMelancholy.class)
            .SetPower(2, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TouhouProject);

    public MedicineMelancholy()
    {
        super(DATA);

        Initialize(0, 0, 25);
        SetCostUpgrade(-1);

        SetAffinity_Violet(2);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new MedicineMelancholyPower(p, magicNumber));
    }

    public static class MedicineMelancholyPower extends AnimatorPower implements OnAttackSubscriber, OnBlockGainedSubscriber {
        public MedicineMelancholyPower(AbstractCreature owner, int amount) {
            super(owner, MedicineMelancholy.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }


        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            CombatStats.onAttack.Subscribe(this);
            CombatStats.onBlockGained.Subscribe(this);
        }

        @Override
        public void onRemove() {
            super.onRemove();

            CombatStats.onAttack.Unsubscribe(this);
            CombatStats.onBlockGained.Unsubscribe(this);
        }

        @Override
        public void OnAttack(DamageInfo info, int damageAmount, AbstractCreature target)
        {
            if (info.type == DamageInfo.DamageType.NORMAL && GameUtilities.IsMonster(target))
            {
                GameActions.Bottom.ApplyPoison(TargetHelper.Normal(target), 1);
            }
        }

        @Override
        public void OnBlockGained(AbstractCreature creature, int block)
        {
            if (this.amount > 0) {
                for (AbstractCreature enemy : GameUtilities.GetEnemies(true)) {
                    PoisonPower poison = GameUtilities.GetPower(enemy, PoisonPower.class);
                    if (poison != null && poison.amount > 0)
                    {
                       int triggerAmount = (int)(poison.amount * (amount / 100f));

                       if (triggerAmount > 0) {
                           GameActions.Top.Callback(new PoisonLoseHpAction(enemy, player, triggerAmount, AttackEffects.POISON));
                       }
                    }
                }
            }
        }
    }
}