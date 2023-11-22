package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class DwarfShaman extends AnimatorCard
{
    public static final EYBCardData DATA = Register(DwarfShaman.class)
            .SetSkill(3, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public DwarfShaman()
    {
        super(DATA);

        Initialize(0, 15, 8);
        SetUpgrade(0, 8, 4);

        SetAffinity_Brown(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.GainTemporaryThorns(magicNumber);
        }
    }


    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        for (AbstractCard card : player.hand.group) {
            if (GameUtilities.IsHindrance(card)) {
                return true;
            }
        }

        return false;
    }
}