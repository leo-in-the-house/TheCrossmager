package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kaijin extends AnimatorCard implements OnAddToDeckListener
{
    public static final EYBCardData DATA = Register(Kaijin.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Kaijin()
    {
        super(DATA);

        Initialize(0, 0, 8);
        SetUpgrade(0, 0, 3);

        SetAffinity_Teal(1);
        SetAffinity_Brown(1);
    }

    @Override
    protected void OnUpgrade()
    {
        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new KaijinPower(p, magicNumber));
    }

    public static class KaijinPower extends AnimatorPower
    {
        public static final String POWER_ID = CreateFullID(KaijinPower.class);

        public KaijinPower(AbstractPlayer owner, int amount)
        {
            super(owner, Kaijin.DATA);

            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            if (!player.hand.isEmpty())
            {
                GameActions.Bottom.SelectFromHand(name, 1, false)
                .SetOptions(true, true, true)
                .SetMessage(GR.Common.Strings.HandSelection.MoveToDrawPile)
                .AddCallback(cards ->
                {
                    if (cards.size() > 0)
                    {
                        for (AbstractCard c : cards)
                        {
                            GameActions.Top.MoveCard(c, player.hand, player.drawPile)
                                    .SetDestination(CardSelection.Top);
                        }

                        final AbstractCard card = cards.get(0);
                        if (card.baseBlock >= 0)
                        {
                            card.baseBlock += amount;
                        }
                        if (card.baseDamage >= 0)
                        {
                            card.baseDamage += amount;
                        }

                        CostModifiers.For(card).Add(1);
                    }
                });
            }
        }
    }
}