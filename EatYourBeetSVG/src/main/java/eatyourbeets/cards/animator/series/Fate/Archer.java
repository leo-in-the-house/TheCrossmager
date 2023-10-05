package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Archer extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Archer.class)
            .SetPower(1, CardRarity.UNCOMMON)
            
            .SetSeriesFromClassPackage();

    public Archer()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Green(1);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new ArcherPower(p, magicNumber));
    }

    public class ArcherPower extends AnimatorPower
    {
        public ArcherPower(AbstractCreature owner, int amount)
        {
            super(owner, Archer.DATA);

            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            SetEnabled(true);

            final int black = CombatStats.Affinities.GetAffinityLevel(Affinity.Black);
            for (int i = 0; i < black; i++)
            {
                AbstractMonster monster = GameUtilities.GetRandomEnemy(true);
                GameActions.Bottom.VFX(VFX.ThrowDagger(monster.hb, 0.2f));
                GameActions.Bottom.DealDamage(owner, monster, amount, DamageInfo.DamageType.THORNS, AttackEffects.NONE)
                .SetVFX(true, true);
            }

            this.flash();
        }
    }
}