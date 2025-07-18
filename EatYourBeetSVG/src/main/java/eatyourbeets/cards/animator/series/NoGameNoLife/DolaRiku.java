package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.DolaRikuAction;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class DolaRiku extends AnimatorCard
{
    public static final EYBCardData DATA = Register(DolaRiku.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .ModifyRewards((data, rewards) ->
            {
                if (DolaSchwi.DATA.GetTotalCopies(player.masterDeck) <= 0)
                {
                    GR.Common.Dungeon.TryReplaceFirstCardReward(rewards, 0.1f, false, DolaSchwi.DATA);
                }
            });

    public DolaRiku()
    {
        super(DATA);

        Initialize(0, 3, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Brown(1, 0, 1);
        SetAffinity_Teal(1, 0, 1);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ExhaustFromHand(name, 1, false)
                .SetOptions(false, false, false)
                .AddCallback(cards -> GameActions.Bottom.Add(new DolaRikuAction(cards.get(0), magicNumber)));
    }
}