package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class King extends AnimatorCard
{
    public static final EYBCardData DATA = Register(King.class)
            .SetSkill(UNPLAYABLE_COST, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public King()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Brown(1);
        SetUnplayable(true);
    }

    @Override
    public void triggerWhenDrawn()
    {
        super.triggerWhenDrawn();

        RandomizedList<AbstractCard> cards = new RandomizedList<>();

        for (AbstractCard card : player.hand.group) {
            if (GameUtilities.HasDamageOrBlock(card) && GameUtilities.HasAnyScaling(card)) {
                cards.Add(card);
            }
        }

        for (int i=0; i<magicNumber; i++) {
            if (cards.Size() > 0) {
                GameActions.Bottom.IncreaseExistingScaling(cards.Retrieve(rng, true), 1);
            }
        }

        GameActions.Bottom.Flash(this);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

    }
}