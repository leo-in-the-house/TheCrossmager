package eatyourbeets.cards.animator.series.Fate;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.Colors;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Lancer extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Lancer.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Piercing)
            .SetSeriesFromClassPackage();

    public Lancer()
    {
        super(DATA);

        Initialize(7, 0, 1);
        SetUpgrade(3, 0, 1);

        SetAffinity_Red(1);
        SetAffinity_Green(1);

        SetAffinityRequirement(Affinity.White, 7);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SPEAR).SetVFXColor(Colors.Lerp(Color.SCARLET, Color.WHITE, 0.3f), Color.RED);

        GameActions.Bottom.ApplyVulnerable(p, m, magicNumber);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.StackPower(new LancerPower(p, 2));
        }
    }

    public static class LancerPower extends AnimatorPower
    {
        private static final float MODIFIER = 100f;

        public LancerPower(AbstractCreature owner, int amount)
        {
            super(owner, Lancer.DATA);

            Initialize(amount, PowerType.BUFF, true);
        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(0);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.EnemyVulnerableModifier += MODIFIER;
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.EnemyVulnerableModifier -= MODIFIER;
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            ReducePower(1);
        }
    }
}