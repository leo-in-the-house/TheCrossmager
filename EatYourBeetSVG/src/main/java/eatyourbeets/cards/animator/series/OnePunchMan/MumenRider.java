package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MumenRider extends AnimatorCard implements OnStartOfTurnPostDrawSubscriber
{
    public static final EYBCardData DATA = Register(MumenRider.class)
            .SetAttack(0, CardRarity.COMMON)
            .SetSeriesFromClassPackage();

    private int turns;

    public MumenRider()
    {
        super(DATA);

        Initialize(3, 0, 3);
        SetUpgrade(2, 0, -1);

        SetAffinity_Green(1);

        SetRicochet(3, -1, this::OnCooldownCompleted);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SMASH);
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.MoveCard(this, player.exhaustPile, player.hand)
                .ShowEffect(true, false);
    }
}