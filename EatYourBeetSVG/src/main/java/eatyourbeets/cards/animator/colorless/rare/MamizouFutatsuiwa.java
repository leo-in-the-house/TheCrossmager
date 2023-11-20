package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MamizouFutatsuiwa extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MamizouFutatsuiwa.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TouhouProject);

    public MamizouFutatsuiwa()
    {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 2);

        SetAffinity_Star(1, 1, 0);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.Draw(magicNumber)
        .AddCallback(cards -> {

            for (AbstractCard card : cards) {
                if (card instanceof AnimatorCard) {
                    GameUtilities.AddAffinityToCard(card, Affinity.Star, 1);
                }
            }
        });
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.SelectFromHand(name, 1, false)
        .SetOptions(false, false, false)
        .SetMessage(RetainCardsAction.TEXT[0])
        .AddCallback(cards ->
        {
            for (AbstractCard c : cards)
            {
                if (GameUtilities.GetAffinityLevel(c, Affinity.Star, true, false) <= 0)
                {
                    GameActions.Bottom.ModifyAffinityLevel(c, Affinity.Star, 1, false);
                }
            }
        });
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        CombatStats.Affinities.AddAffinitySealUses(1);
    }
}
