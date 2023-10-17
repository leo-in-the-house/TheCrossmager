package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YunYun extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YunYun.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public YunYun()
    {
        super(DATA);

        Initialize(10, 0);
        SetUpgrade(8, 0);

        SetAffinity_Yellow(2);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        super.triggerOnOtherCardPlayed(c);

        GameActions.Bottom.Callback(this::RefreshCost);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        RefreshCost();
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.LIGHTNING);
    }

    public void RefreshCost()
    {
        int konosubaCards = 0;
        for (AbstractCard c : player.hand.group)
        {
            if (c != this && c instanceof AnimatorCard && ((AnimatorCard) c).series.Equals(CardSeries.Konosuba))
            {
                konosubaCards += 1;
            }
        }

        CostModifiers.For(this).Set(-konosubaCards);
    }
}