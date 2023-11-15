package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class GazelDwargon extends AnimatorCard
{
    public static final EYBCardData DATA = Register(GazelDwargon.class)
            .SetSkill(X_COST, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public GazelDwargon()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Brown(1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        final int stacks = GameUtilities.UseXCostEnergy(this);
        GameActions.Bottom.GainPlatedArmor(stacks * magicNumber);
        GameActions.Bottom.GainBrown(stacks * magicNumber);
    }
}