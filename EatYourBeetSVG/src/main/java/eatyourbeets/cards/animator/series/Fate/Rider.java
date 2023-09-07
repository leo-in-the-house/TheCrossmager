package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Rider extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Rider.class)
            .SetSkill(2, CardRarity.COMMON)
            .SetSeriesFromClassPackage();

    public Rider()
    {
        super(DATA);

        Initialize(0, 7, 4);
        SetUpgrade(0, 4, 2);

        SetAffinity_Green(1);
        SetAffinity_Black(1);
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        super.OnDrag(m);

        if (m != null)
        {
            GameUtilities.GetIntent(m).AddStrength(-magicNumber);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        for (AbstractMonster target : GameUtilities.GetEnemies(true)) {
            GameActions.Bottom.ReduceStrength(target, magicNumber, true);
        }
    }
}