package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.vfx.combat.FallingIceEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Cirno extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Cirno.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Cirno()
    {
        super(DATA);

        Initialize(3, 0, 2, 2);
        SetUpgrade(1, 0, 0, 2);

        SetAffinity_Blue(1, 0, 0);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Top.WaitRealtime(0.3f);
        GameActions.Bottom.Callback(() ->
        {
            MonsterGroup monsters = AbstractDungeon.getMonsters();
            int frostCount = monsters.monsters.size() + 5;

            for (int i = 0; i < frostCount; i++)
            {
                GameEffects.Queue.Add(new FallingIceEffect(frostCount, monsters.shouldFlipVfx()));
            }

            GameActions.Top.WaitRealtime(0.3f);
        });

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.BLUNT_LIGHT)
                    .SetVFX(true, false);
        }

        GameActions.Bottom.StackPower(new CirnoPower(player, secondaryValue));
    }

    public static class CirnoPower extends AnimatorPower implements OnBlockGainedSubscriber {
        public CirnoPower(AbstractCreature owner, int amount) {
            super(owner, Cirno.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            CombatStats.onBlockGained.Subscribe(this);
        }

        @Override
        public void onRemove() {
            super.onRemove();

            CombatStats.onBlockGained.Unsubscribe(this);
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
        public void OnBlockGained(AbstractCreature creature, int block)
        {
            GameActions.Bottom.Callback(() -> {
                if (creature == owner && this.amount > 0) {
                    for (AbstractMonster monster : GameUtilities.GetEnemies(true)) {
                        if (GameUtilities.IsAttacking(monster.intent)) {
                            GameActions.Top.ApplyFreezing(TargetHelper.Normal(monster), 1);
                        }
                    }
                    ReducePower(1);
                }
            });
        }
    }
}

