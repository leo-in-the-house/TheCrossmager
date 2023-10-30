package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.tokens.AffinityToken;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;

public class FielNirvalen extends AnimatorCard
{
    public static final EYBCardData DATA = Register(FielNirvalen.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreviews(AffinityToken.GetCards(), true));

    public FielNirvalen()
    {
        super(DATA);

        Initialize(0, 0, 0, 0);
        SetUpgrade(0, 0, 0, 0);

        SetAffinity_Blue(2);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new FielNirvalenPower(p, secondaryValue, upgraded));
    }

    public static class FielNirvalenPower extends AnimatorPower
    {
        private boolean upgraded;

        public FielNirvalenPower(AbstractCreature owner, int amount, boolean upgraded)
        {
            super(owner, FielNirvalen.DATA);

            this.amount = amount;
            this.upgraded = upgraded;

            updateDescription();
        }

        @Override
        public void onPlayCard(AbstractCard card, AbstractMonster m) {
            super.onPlayCard(card, m);

            if (card.costForTurn == 0) {

                ArrayList<AbstractCard> cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn;

                int numZeroCostsPlayed = (int) cardsPlayed.stream().filter(c -> c.costForTurn == 0).count();

                if (numZeroCostsPlayed % 2 == 0) {
                    GameActions.Bottom.MakeCardInHand(AffinityToken.GetRandomCard());
                }
            }
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}