package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Benimaru extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Benimaru.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Benimaru()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Red(1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DiscardFromHand(name, magicNumber, false)
            .SetOptions(true, true, true)
            .SetFilter(GameUtilities::IsHighCost)
            .AddCallback(cards -> {
               for (AbstractCard card : cards) {
                   int cost = card.costForTurn;

                   if (cost > 0) {
                       GameActions.Bottom.ApplyBurning(TargetHelper.RandomEnemy(), cost);
                   }
               }
            });
    }
}