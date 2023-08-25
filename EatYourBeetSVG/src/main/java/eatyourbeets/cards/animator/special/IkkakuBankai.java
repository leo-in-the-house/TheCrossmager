package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class IkkakuBankai extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IkkakuBankai.class).SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Piercing, EYBCardTarget.ALL).SetSeries(CardSeries.Bleach);

    public IkkakuBankai()
    {
        super(DATA);

        Initialize(12, 0, 0);
        SetUpgrade(4, 0, 0);

        SetAffinity_Red(2);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        int stacks = GameUtilities.UseXCostEnergy(this);

        for (int i=0; i<stacks-1; i++) {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HORIZONTAL);
        }

        if (stacks > 0) {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HORIZONTAL)
                    .SetSoundPitch(0.5f, 0.7f)
                    .SetDuration(1.5f, false);
        }
    }
}