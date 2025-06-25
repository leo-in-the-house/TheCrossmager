package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.status.Status_Slimed;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Hans extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Hans.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(GetClassCard(Slimed.ID), true))
            .ModifyRewards((data, rewards) ->
            {
                if (Sylvia.DATA.GetTotalCopies(player.masterDeck) <= 0)
                {
                    GR.Common.Dungeon.TryReplaceFirstCardReward(rewards, 0.1f, false, Sylvia.DATA);
                }
            });
    public static final int SLIMED_AMOUNT = 3;

    public Hans()
    {
        super(DATA);

        Initialize(0, 0, 4, 3);
        SetUpgrade(0, 0, 3);

        SetAffinity_Violet(1, 0, 0);

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
        GameActions.Bottom.MakeCardInDrawPile(new Status_Slimed(false))
                .Repeat(secondaryValue);
        GameActions.Bottom.StackPower(new HansPower(p, magicNumber));
    }

    public static class HansPower extends AnimatorPower
    {
        public HansPower(AbstractCreature owner, int amount)
        {
            super(owner, Hans.DATA);

            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            GameActions.Bottom.ApplyPoison(TargetHelper.Enemies(), amount);
            flash();
        }
    }
}