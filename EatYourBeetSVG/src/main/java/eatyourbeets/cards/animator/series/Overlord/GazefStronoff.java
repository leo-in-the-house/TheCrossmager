package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class GazefStronoff extends AnimatorCard
{
    public static final EYBCardData DATA = Register(GazefStronoff.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)

            .SetSeriesFromClassPackage();

    public GazefStronoff()
    {
        super(DATA);

        Initialize(0, 12, 3);
        SetUpgrade(0, 8);

        SetAffinity_White(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        int numCommonDebuffs = GameUtilities.GetCommonDebuffs(TargetHelper.Enemies()).size();

        if (numCommonDebuffs > 0) {
            GameActions.Bottom.GainWhite(numCommonDebuffs * magicNumber);
        }
    }
}