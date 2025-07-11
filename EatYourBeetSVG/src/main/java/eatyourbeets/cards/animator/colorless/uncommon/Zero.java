package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.RotatingList;

public class Zero extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Zero.class)
            .SetSkill(1, CardRarity.UNCOMMON)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GrimoireOfZero);

    public Zero()
    {
        super(DATA);

        Initialize(0, 0, 0);

        SetAffinity_Blue(1);
        SetAffinity_White(1);

        SetCardPreview(this::FindCards);
        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetExhaust(false);
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.PlayFromPile(name, 1, m, p.drawPile)
        .SetOptions(true, false)
        .SetFilter(c -> c.type == CardType.SKILL && GameUtilities.IsHighCost(c));
    }

    protected void FindCards(RotatingList<AbstractCard> cards, AbstractMonster target)
    {
        cards.Clear();
        for (AbstractCard c : player.drawPile.group)
        {
            if (c.type == CardType.SKILL && GameUtilities.IsHighCost(c) && GameUtilities.IsPlayable(c, target) && !c.tags.contains(VOLATILE))
            {
                cards.Add(c);
            }
        }
    }
}