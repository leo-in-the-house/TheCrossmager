package eatyourbeets.cards.animator.series.Elsword;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.*;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

import java.util.ArrayList;

public class Add extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Add.class)
            .SetSkill(4, CardRarity.RARE, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage()
            .ObtainableAsReward((data, deck) -> deck.size() >= (14 + (10 * data.GetTotalCopies(deck))))
            .PostInitialize(data -> {
                data.AddPreview(new OrbCore_Fire(), false);
                data.AddPreview(new OrbCore_Frost(), false);
                data.AddPreview(new OrbCore_Chaos(), false);
                data.AddPreview(new OrbCore_Dark(), false);
            });

    public Add()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 0);

        SetRetain(true);
        SetAffinity_Teal(1);
        SetAffinity_Violet(1);

        SetCostUpgrade(-1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ExhaustFromPile(name, 1, p.hand, p.drawPile, p.discardPile)
                .AddCallback(this::OnCardChosen);
        GameActions.Bottom.MakeCardInHand(new OrbCore_Dark());
    }

    private void OnCardChosen(ArrayList<AbstractCard> cards)
    {
        if (cards != null && cards.size() > 0)
        {
            final AbstractCard c = cards.get(0);

            if (GameUtilities.HasAffinity(c, Affinity.Red))
            {
                GameActions.Bottom.MakeCardInHand(new OrbCore_Fire());
            }
            if (GameUtilities.HasAffinity(c, Affinity.Green))
            {
                GameActions.Bottom.MakeCardInHand(new OrbCore_Frost());
            }
            if (GameUtilities.HasAffinity(c, Affinity.Blue))
            {
                GameActions.Bottom.MakeCardInHand(new OrbCore_Chaos());
            }
        }
    }

    private void OrbChosen(CardGroup cardGroup, ArrayList<AbstractCard> chosen)
    {
        if (cardGroup != null && chosen != null && chosen.size() == 1)
        {
            switch (cardGroup.type)
            {
                case DRAW_PILE:
                case HAND:
                case DISCARD_PILE:
                    GameActions.Bottom.MakeCard(chosen.get(0), cardGroup);
                    break;

                default:
                    JUtils.LogWarning(this, "Invalid card group type: " + cardGroup.type);
                    break;
            }
        }
    }
}