package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MattheusCallaway extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MattheusCallaway.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public MattheusCallaway()
    {
        super(DATA);

        Initialize(0, 6, 6);
        SetUpgrade(0, 2, 2);

        SetAffinity_Teal(1, 0, 0);
    }

    @Override
    protected float GetInitialBlock()
    {
        return super.GetInitialBlock() + (GameActionManager.totalDiscardedThisTurn > 0 ? magicNumber : 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
    }
}