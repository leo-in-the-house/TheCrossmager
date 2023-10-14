package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Hitei extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Hitei.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Hitei()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Pink(1);
        SetAffinity_Violet(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new HiteiPower(p, magicNumber));
    }

    public static class HiteiPower extends AnimatorPower
    {
        public HiteiPower(AbstractPlayer owner, int amount)
        {
            super(owner, Hitei.DATA);

            Initialize(amount);
        }

        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            for (int i = 0; i < amount; i++)
            {
                GameActions.Bottom.Callback(() ->
                {
                    final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    final RandomizedList<AbstractCard> pile;
                    if (player.drawPile.size() < amount)
                    {
                        group.group.addAll(player.drawPile.group);
                        pile = new RandomizedList<>(player.discardPile.group);
                    }
                    else
                    {
                        pile = new RandomizedList<>(player.drawPile.group);
                    }

                    while (group.size() < amount && pile.Size() > 0)
                    {
                        group.addToTop(pile.Retrieve(rng));
                    }

                    if (group.size() >= 0)
                    {
                        GameActions.Top.ExhaustFromPile(name, 1, group)
                        .ShowEffect(true, true)
                        .SetOptions(false, false)
                        .AddCallback(cards ->
                        {
                            for (AbstractCard c : cards)
                            {
                                GameActions.Bottom.SealAffinities(c, false);
                            }
                        });
                    }
                });
            }

            this.flash();
        }
    }
}