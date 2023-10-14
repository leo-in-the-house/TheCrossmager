package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.tokens.AffinityToken;
import eatyourbeets.cards.base.*;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Togame extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Togame.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(AffinityToken.GetCard(Affinity.Pink), true);
                data.AddPreview(AffinityToken.GetCard(Affinity.Black), true);
            });

    public Togame()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Pink(1);

        SetExhaust(true);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Draw(magicNumber)
        .AddCallback(cardsDrawn -> {
            GameActions.Top.SelectFromHand(name, 1, false)
                    .SetFilter(card -> !GameUtilities.IsSealed(card))
                    .SetMessage(GR.Common.Strings.HandSelection.Seal)
                    .SetOptions(false, false, false)
                    .AddCallback(cards ->
                    {
                        for (AbstractCard c : cards)
                        {
                            if (GameUtilities.IsHindrance(c)) {
                                GameActions.Top.ObtainAffinityToken(Affinity.Black, upgraded);
                            } else {
                                GameActions.Top.ObtainAffinityToken(Affinity.Pink, upgraded);
                            }

                            GameActions.Top.SealAffinities(c);
                        }
                    });
        });
    }
}