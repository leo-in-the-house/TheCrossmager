package eatyourbeets.cards.animator.series.Elsword;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ciel extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Ciel.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Lu(), false))
            .ModifyRewards((data, rewards) ->
            {
                if (Lu.DATA.GetTotalCopies(player.masterDeck) <= 0)
                {
                    GR.Common.Dungeon.TryReplaceFirstCardReward(rewards, 0.01f, false, Lu.DATA);
                }
            });

    public Ciel()
    {
        super(DATA);

        Initialize(0, 4, 12, 2);
        SetUpgrade(0, 2, 4, 2);

        SetAffinity_Red(1, 0, 0);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Blue(1, 0, 0);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.GainRed(secondaryValue);
        GameActions.Bottom.GainGreen(secondaryValue);
        GameActions.Bottom.GainBlue(secondaryValue);
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return GameUtilities.GetAllInBattleCopies(Lu.DATA.ID).size() > 0 && super.CheckSpecialCondition(tryUse);
    }
}