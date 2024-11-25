package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Serara extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Serara.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeries(CardSeries.LogHorizon);

    public Serara()
    {
        super(DATA);

        Initialize(0, 6, 2);
        SetUpgrade(0, 4, 0);

        SetAffinity_Brown(1);
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ExhaustFromPile(name, magicNumber, player.drawPile)
                .SetOptions(true, true)
                .SetFilter(GameUtilities::IsHindrance);
    }
}