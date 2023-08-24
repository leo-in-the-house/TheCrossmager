package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;

public class MasamiIwasawa extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MasamiIwasawa.class)
            .SetSkill(2, CardRarity.COMMON)
            .SetSeriesFromClassPackage();

    public MasamiIwasawa()
    {
        super(DATA);

        Initialize(0, 20, 2, 1);
        SetUpgrade(0, 8, 0, 0);

        SetAffinity_Light(1);
        SetAffinity_Blue(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.MakeCardInDrawPile(new Dazed())
        .SetDestination(CardSelection.Top)
        .Repeat(secondaryValue);
    }
}