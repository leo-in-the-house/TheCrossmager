package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;

public class Guy extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Guy.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeries(CardSeries.HitsugiNoChaika);

    public Guy()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Violet(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Draw(magicNumber);
        GameActions.Bottom.DiscardFromHand(name, 1, false)
        .SetOptions(false, false, true);
    }
}