package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.common.InsanityPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class InverseTohka extends AnimatorCard
{
    public static final EYBCardData DATA = Register(InverseTohka.class)
            .SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.ALL).SetSeries(CardSeries.DateALive)
            .SetSeries(CardSeries.DateALive);

    public InverseTohka()
    {
        super(DATA);

        Initialize(8, 0, 2, 1);
        SetUpgrade(3, 0);
        SetAffinity_Black(2, 0, 2);
    }

    @Override
    protected void OnUpgrade()
    {
        AddScaling(Affinity.Black, 3);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        super.triggerOnOtherCardPlayed(c);

        if (c.type == CardType.SKILL)
        {
            int half_amount = c.block / 2;

            if (half_amount > 0) {
                GameActions.Bottom.GainBlack(half_amount);
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HEAVY);
        GameActions.Bottom.StackPower(new InsanityPower(p, 1));
    }
}