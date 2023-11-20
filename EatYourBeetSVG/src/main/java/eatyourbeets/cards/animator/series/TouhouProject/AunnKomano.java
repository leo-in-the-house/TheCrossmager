package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.orbs.animator.Earth;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AunnKomano extends AnimatorCard {
    public static final EYBCardData DATA = Register(AunnKomano.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public AunnKomano() {
        super(DATA);

        Initialize(0, 4, 3);
        SetUpgrade(0, 0, 2);

        SetAffinity_Brown(2);
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

        if (GameUtilities.HasOrb(Frost.ORB_ID)) {
            GameActions.Bottom.ChannelOrb(new Earth());
        }
        else {
            GameActions.Bottom.ChannelOrb(new Frost());
        }
    }
}