package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.interfaces.subscribers.OnModifyDamageFirstSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class AzusaShirasu extends AnimatorCard {
    public static final EYBCardData DATA = Register(AzusaShirasu.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public AzusaShirasu() {
        super(DATA);

        Initialize(0, 9, 2);
        SetUpgrade(0, 8, 0);

        SetAffinity_Blue(2, 0, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            if (GameUtilities.IsAttacking(enemy.intent)) {
                GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(enemy), magicNumber);
            }
        }

        GameActions.Bottom.StackPower(new AzusaShirasuPower(p, 100));
    }

    public static class AzusaShirasuPower extends AnimatorPower implements OnModifyDamageFirstSubscriber {
        public AzusaShirasuPower(AbstractPlayer owner, int amount) {
            super(owner, AzusaShirasu.DATA);

            Initialize(amount);
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
        public int OnModifyDamageFirst(AbstractCreature target, DamageInfo info, int damage)
        {
            if (target != player && (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner.isPlayer) && target.hasPower(LockOnPower.POWER_ID))
            {
                flash();
                return damage + (damage * (amount / 100));
            }

            return damage;
        }
    }

}