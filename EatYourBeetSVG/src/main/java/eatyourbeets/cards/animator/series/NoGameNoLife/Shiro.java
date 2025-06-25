package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Shiro extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shiro.class)
            .SetPower(4, CardRarity.RARE)

            .SetSeriesFromClassPackage()
            .ModifyRewards((data, rewards) ->
            {
                if (Sora.DATA.GetTotalCopies(player.masterDeck) <= 0)
                {
                    GR.Common.Dungeon.TryReplaceFirstCardReward(rewards, 0.1f, false, Sora.DATA);
                }
            });

    public Shiro()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Pink(2);
        SetAffinity_White(2);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        super.triggerOnOtherCardPlayed(c);

        GameActions.Bottom.Callback(this::RefreshCost);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        RefreshCost();
    }

    public void RefreshCost()
    {
        CostModifiers.For(this).Set("shiro",-1 * JUtils.Count(player.exhaustPile.group, card -> (card.costForTurn == 0)));
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new ShiroPower(player, magicNumber));
    }

    public static class ShiroPower extends AnimatorPower
    {
        public ShiroPower(AbstractCreature owner, int amount)
        {
            super(owner, Shiro.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            GameActions.Bottom.GainEnergy(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}