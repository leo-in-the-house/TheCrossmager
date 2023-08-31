package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.cardManipulation.RandomCardUpgrade;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;

public class EmiyaShirou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(EmiyaShirou.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public EmiyaShirou()
    {
        super(DATA);

        Initialize(0, 5, 2);
        SetUpgrade(0, 2, 1);

        SetAffinity_Red(1);
        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.Add(new RandomCardUpgrade());
        }

        boolean fullyUpgraded = true;
        for (AbstractCard card : p.hand.group)
        {
            if (card != this && !card.upgraded && !GameUtilities.IsHindrance(card))
            {
                fullyUpgraded = false;
                break;
            }
        }

        if (fullyUpgraded)
        {
            GameActions.Bottom.Draw(2);
        }
    }
}