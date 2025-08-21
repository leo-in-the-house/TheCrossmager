package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class AzusaShirasu extends AnimatorCard {
    public static final EYBCardData DATA = Register(AzusaShirasu.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public AzusaShirasu() {
        super(DATA);

        Initialize(0, 4, 2);
        SetUpgrade(0, 2, 2);

        SetExhaust(true);

        SetAffinity_Blue(2, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            if (GameUtilities.IsAttacking(enemy.intent)) {
                GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(enemy), magicNumber);
                GameActions.Bottom.StackPower(new AzusaShirasuPower(enemy, 100));
            }
        }

    }

    public static class AzusaShirasuPower extends AnimatorPower {
        public AzusaShirasuPower(AbstractCreature owner, int amount) {
            super(owner, AzusaShirasu.DATA);

            Initialize(amount, PowerType.DEBUFF, false);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atStartOfTurn()
        {
            RemovePower();
        }


        @Override
        public float atDamageReceive(float damage, DamageInfo.DamageType type)
        {
            if (type == DamageInfo.DamageType.NORMAL)
            {
                damage += (damage * (amount / 100));
            }

            return super.atDamageReceive(damage, type);
        }
    }

}