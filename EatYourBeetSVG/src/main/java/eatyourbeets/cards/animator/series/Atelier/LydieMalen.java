package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.SuelleMalen;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class LydieMalen extends AnimatorCard {
    public static final EYBCardData DATA = Register(LydieMalen.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new SuelleMalen(), false);
    }

    public LydieMalen() {
        super(DATA);

        Initialize(0, 4, 2, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Pink(1, 0, 1);
        SetAffinity_Blue(1, 0, 1);
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

        GameActions.Bottom.ApplyVulnerable(TargetHelper.Enemies(), secondaryValue);

        GameActions.Bottom.MakeCardInDrawPile(new SuelleMalen());

        GameActions.Last.Exhaust(this);
    }
}