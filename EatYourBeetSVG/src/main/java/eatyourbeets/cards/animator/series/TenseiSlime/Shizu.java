package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Shizu_Ifrit;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.orbs.animator.Fire;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Shizu extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shizu.class)
            .SetAttack(3, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Shizu_Ifrit(), true));

    public Shizu()
    {
        super(DATA);

        Initialize(10, 0, 2, 10);
        SetUpgrade(5, 0);

        SetAffinity_Red(2, 0, 2);
        SetAffinity_Brown(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE)
        .SetDamageEffect(c -> GameEffects.List.Attack(player, c, AttackEffects.SLASH_DIAGONAL, 0.9f, 1.1f).duration * 0.33f);
        GameActions.Bottom.StackPower(new ShizuPower(p, 2));

        if (CheckSpecialCondition(false) && CombatStats.TryActivateLimited(cardID))
        {
            GameActions.Bottom.MakeCardInHand(new Shizu_Ifrit()).SetUpgrade(upgraded, false);
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        if (CombatStats.CanActivateLimited(cardID) && (GameUtilities.GetTotalCostOfCardsInHand() >= 10))
        {
            return true;
        }

        return false;
    }

    public static class ShizuPower extends AnimatorPower {
        public ShizuPower(AbstractCreature owner, int amount) {
            super(owner, Shizu.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target)
        {
            super.onAttack(info, damageAmount, target);

            if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL)
            {
                GameActions.Bottom.ChannelOrb(new Fire());
                this.flash();
            }
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            ReducePower(1);
        }
    }
}