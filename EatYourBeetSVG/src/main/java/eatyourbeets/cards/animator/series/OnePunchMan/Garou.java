package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Garou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Garou.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .ObtainableAsReward((data, deck) -> deck.size() >= (14 + (7 * data.GetTotalCopies(deck))));

    public Garou()
    {
        super(DATA);

        Initialize(0, 0, 7, 5);

        SetAffinity_Red(1);
        SetAffinity_Green(1);
        SetAffinity_Violet(1);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetExhaust(false);
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        SetUnplayable(player.drawPile.size() < secondaryValue);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        if (p.drawPile.size() >= secondaryValue)
        {
            GameActions.Bottom.MoveCards(p.drawPile, p.exhaustPile, secondaryValue)
            .ShowEffect(true, true)
            .SetOrigin(CardSelection.Top)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    if (card instanceof AnimatorCard) {
                        if (GameUtilities.HasAnyScaling(card)) {
                            EYBCardAffinities affinities = ((AnimatorCard) card).affinities;
                            GameActions.Top.GainTemporaryStats(1, 1, 0);

                            for (EYBCardAffinity affinity : affinities.List) {
                                if (affinity.scaling > 0) {
                                    GameActions.Top.GainAffinity(affinity.type, affinity.scaling);
                                }
                            }

                        }
                    }
                }
           });
        }
    }
}