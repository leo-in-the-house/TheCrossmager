package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.LockOn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnDamageOverrideSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;
import eatyourbeets.utilities.TargetHelper;

public class AsukaLangleySoryu extends AnimatorCard {
    public static final EYBCardData DATA = Register(AsukaLangleySoryu.class)
            .SetAttack(2, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.ALL);

    public AsukaLangleySoryu() {
        super(DATA);

        Initialize(8, 0, 3);
        SetUpgrade(8, 0, 0);

        SetAffinity_Teal(1, 0, 1);
        SetAffinity_Red(1, 0, 1);

        SetSeries(CardSeries.Evangelion);

        SetAffinityRequirement(Affinity.Teal, 2);
        SetAffinityRequirement(Affinity.Red, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.GUNSHOT);

        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.ApplyLockOn(TargetHelper.Enemies(), magicNumber);
        }

        GameActions.Bottom.StackPower(new AsukaLangleyPower(p));
    }

    public static class AsukaLangleyPower extends AnimatorPower implements OnDamageOverrideSubscriber
    {
        public AsukaLangleyPower(AbstractPlayer owner)
        {
            super(owner, AsukaLangleySoryu.DATA);

            Initialize(-1);
            CombatStats.onDamageOverride.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onDamageOverride.Unsubscribe(this);
        }

        @Override
        public void onPlayCard(AbstractCard card, AbstractMonster m)
        {
            super.onPlayCard(card,m);
            AnimatorCard aCard = JUtils.SafeCast(card, AnimatorCard.class);
            if (m != null && m.hasPower(LockOn.ID) && aCard != null && aCard.damage > 0 && aCard.damageTypeForTurn == DamageInfo.DamageType.NORMAL)
            {
                this.flash();
            }
        }

        @Override
        public float OnDamageOverride(AbstractCreature target, DamageInfo.DamageType type, float damage, AbstractCard card)
        {
            AnimatorCard aCard = JUtils.SafeCast(card,AnimatorCard.class);
            if (aCard != null && damage < aCard.baseDamage && target.hasPower(LockOn.ID)) {
                return aCard.baseDamage;
            }
            return damage;
        }

    }
}