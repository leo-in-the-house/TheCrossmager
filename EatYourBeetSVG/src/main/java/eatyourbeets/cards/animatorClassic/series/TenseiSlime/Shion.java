package eatyourbeets.cards.animatorClassic.series.TenseiSlime;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;

public class Shion extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Shion.class).SetSeriesFromClassPackage().SetAttack(2, CardRarity.COMMON);

    public Shion()
    {
        super(DATA);

        Initialize(16, 0, 2);
        SetUpgrade(5, 0, 0);
        SetScaling(0, 0, 1);

        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);

        if (info.IsSynergizing && CombatStats.TryActivateLimited(cardID))
        {
            GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
        }
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DiscardFromHand(name, 1, false)
        .SetOptions(false, false, false);
        GameActions.Bottom.StackPower(new DrawCardNextTurnPower(p, 1));
    }
}