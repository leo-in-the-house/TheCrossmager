package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class DolaCouronne extends AnimatorCard
{
    public static final EYBCardData DATA = Register(DolaCouronne.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public DolaCouronne()
    {
        super(DATA);

        Initialize(0, 11, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.DiscardFromHand(name, 1, false)
            .SetOptions(false, false, false)
            .AddCallback(cards -> {
               for (AbstractCard card : cards) {
                   if (card.costForTurn == 0) {
                       GameActions.Top.Draw(1)
                          .SetFilter(c -> c.costForTurn == 0, false);
                   }
               }
            });
    }
}