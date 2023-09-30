package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.animator.SupportDamagePower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class InverseOrigami extends AnimatorCard
{
    public static final EYBCardData DATA = Register(InverseOrigami.class)
            .SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Ranged, EYBCardTarget.Normal)
            .SetSeries(CardSeries.DateALive);
    public static final int SUPPORT_DAMAGE_COST = 3;

    public InverseOrigami() {
        super(DATA);

        Initialize(1, 0);
        SetAffinity_White(1, 0, 2);
        SetAffinity_Blue(1, 0, 2);
        SetAffinity_Black(1);
    }

    @Override
    protected void OnUpgrade()
    {
        AddScaling(Affinity.White, 2);
        AddScaling(Affinity.Blue, 2);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        super.triggerOnOtherCardPlayed(c);

        if (c.type == CardType.SKILL)
        {
            int half_amount = c.block / 2;

            if (half_amount > 0) {
                GameActions.Bottom.StackPower(new SupportDamagePower(player, half_amount));
            }
        }
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.LIGHTNING);

        GameActions.Bottom.Callback(c -> {
            SupportDamagePower supportDamage = GameUtilities.GetPower(player, SupportDamagePower.class);
            if (supportDamage != null && supportDamage.amount > 0) {
                supportDamage.atEndOfTurn(true);
            }
        });
    }
}