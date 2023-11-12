package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Suiseiseki extends AnimatorCard {
    public static final EYBCardData DATA = Register(Suiseiseki.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Suiseiseki() {
        super(DATA);

        Initialize(0, 5, 2);
        SetUpgrade(0, 4, 0);

        SetAffinity_Green(2);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        int tempThornsToGain = GameUtilities.GetOtherCardsInHand(this).size();

        if (tempThornsToGain > 0) {
            GameActions.Bottom.GainTemporaryThorns(tempThornsToGain);
        }
    }
}