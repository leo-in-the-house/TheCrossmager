package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.orbs.animator.Earth;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Noelle extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Noelle.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.Self)
            .SetSeriesFromClassPackage();

    public Noelle()
    {
        super(DATA);

        Initialize(0, 6, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Brown(1, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChannelOrb(new Earth());
    }
}