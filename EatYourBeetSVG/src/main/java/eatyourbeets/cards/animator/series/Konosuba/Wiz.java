package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Wiz extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Wiz.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage();

    public Wiz()
    {
        super(DATA);

        Initialize(0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(1);
        SetAffinity_Black(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.FetchFromPile(name, 1, p.exhaustPile)
        .SetOptions(false, true)
        .SetMessage(GR.Common.Strings.HandSelection.Obtain)
        .AddCallback(cards -> {
            boolean konosubaCardFetched = false;

            for (AbstractCard card : cards) {
                if (card.costForTurn >= 0) {
                    GameUtilities.ModifyCostForCombat(card, 0, false);
                }

                if (GameUtilities.IsSeries(card, CardSeries.Konosuba)) {
                    konosubaCardFetched = true;
                }
            }

            if (!konosubaCardFetched) {
                GameActions.Last.Purge(uuid)
                  .ShowEffect(true);
            }
        });
    }
}