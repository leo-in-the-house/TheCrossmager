package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class AyaneOkusora extends AnimatorCard {
    public static final EYBCardData DATA = Register(AyaneOkusora.class)
            .SetPower(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public AyaneOkusora() {
        super(DATA);

        Initialize(0, 8, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Pink(1);
        SetAffinity_Yellow(1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainDexterity(magicNumber);
        GameActions.Bottom.StackPower(new AyaneOkusoraPower(p, magicNumber));
    }

    public static class AyaneOkusoraPower extends AnimatorClickablePower {
        public AyaneOkusoraPower(AbstractCreature owner, int amount) {
            super(owner, AyaneOkusora.DATA, PowerTriggerConditionType.Energy, 1);
            this.triggerCondition.SetUses(1, true, true);

            Initialize(amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            playApplyPowerSfx();

            int maxHealth = Integer.MIN_VALUE;
            AbstractMonster enemy = null;

            for (AbstractMonster monster : GameUtilities.GetEnemies(true))
            {
                if (monster.currentHealth > maxHealth)
                {
                    maxHealth = monster.currentHealth;
                    enemy = monster;
                }
            }

            if (enemy != null)
            {
                GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(enemy), amount);
            }
        }
    }
}