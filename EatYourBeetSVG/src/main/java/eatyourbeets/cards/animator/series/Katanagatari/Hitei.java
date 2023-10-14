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
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new HiteiPower(p, 1, magicNumber));
    }

    public static class HiteiPower extends AnimatorPower
    {
        int numChoices = 0;

        public HiteiPower(AbstractPlayer owner, int amount, int numChoices)
        {
            super(owner, Hitei.DATA);

            this.numChoices = Math.max(this.numChoices, numChoices);

            Initialize(amount);
        }

        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            GameActions.Bottom.Callback(() ->
            {
                final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                final RandomizedList<AbstractCard> pile;
                if (player.drawPile.size() < numChoices)
                {
                    group.group.addAll(player.drawPile.group);
                    pile = new RandomizedList<>(player.discardPile.group);
                }
                else
                {
                    pile = new RandomizedList<>(player.drawPile.group);
                }

                while (group.size() < numChoices && pile.Size() > 0)
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
            GameActions.Bottom.Draw(amount);

            this.flash();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount, numChoices);
        }
    }
}