package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.SFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ramiris extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Ramiris.class)
            .SetSkill(X_COST, CardRarity.RARE, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Ramiris()
    {
        super(DATA);

        Initialize(0, 0, 1, 3);
        SetUpgrade(0, 0, 1, 0);

        SetAffinity_Pink(2);

        SetHaste(true);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m)) {
            if (upgraded) {
                return true;
            }

            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn)
            {
                if (c.type == CardType.ATTACK)
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        final int charge = GameUtilities.UseXCostEnergy(this);
        if (charge > 0)
        {
            GameActions.Bottom.SelectFromPile(name, charge, p.drawPile)
            .SetOptions(false, true)
            .SetFilter(c -> GameUtilities.IsHighCost(c))
            .AddCallback(cards ->
            {
                if (cards.size() > 0)
                {
                    GameActions.Top.SFX(SFX.TINGSHA, 1.2f, 1.4f, 0.9f);

                    for (AbstractCard c : cards)
                    {
                        GameActions.Top.MakeCardInHand(GameUtilities.Imitate(c))
                            .AddCallback(card -> {
                                if (card instanceof EYBCard) {
                                    ((EYBCard) card).SetHaste(true);
                                }
                            });
                    }
                }
            });
        }
    }
}