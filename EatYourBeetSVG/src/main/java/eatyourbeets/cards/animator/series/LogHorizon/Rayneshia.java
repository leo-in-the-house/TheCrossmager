package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Rayneshia extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Rayneshia.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Rayneshia()
    {
        super(DATA);

        Initialize(0, 11, 3);
        SetUpgrade(0, 0, 1);

        SetAffinity_White(2, 0, 1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        final CardGroup choice = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        final RandomizedList<AbstractCard> pool = GameUtilities.GetCardPoolInCombat(CardRarity.RARE);

        while (choice.size() < magicNumber && pool.Size() > 0)
        {
            AbstractCard option = pool.Retrieve(rng).makeCopy();
            if (upgraded) {
                option.upgrade();
            }
            choice.addToTop(option);
        }

        GameActions.Bottom.SelectFromPile(name, 1, choice)
        .SetOptions(false, true)
        .AddCallback(cards ->
        {
            if (cards != null && cards.size() > 0)
            {
                AbstractCard chosenCard = cards.get(0);

                if (chosenCard instanceof EYBCard) {
                    ((EYBCard) chosenCard).SetDelayed(true);
                }

                GameActions.Top.MakeCardInDrawPile(chosenCard)
                    .SetDestination(CardSelection.Bottom);
            }
        });
    }
}