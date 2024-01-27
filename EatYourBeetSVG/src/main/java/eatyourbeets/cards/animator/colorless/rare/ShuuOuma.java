package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ShuuOuma extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ShuuOuma.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GuiltyCrown);

    public ShuuOuma()
    {
        super(DATA);

        Initialize(0, 0, 1);

        SetAffinity_Pink(1);
        SetAffinity_Black(1);
        SetCostUpgrade(-1);

        SetRetain(true);
        SetFading(true);

        SetCardPreview(c -> c.type == CardType.POWER);
    }

    @Override
    protected void OnUpgrade()
    {
        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.FetchFromPile(name, magicNumber, player.drawPile)
        .SetOptions(false, false)
        .SetFilter(c -> c.type.equals(CardType.POWER))
        .AddCallback(cards ->
        {
            for (AbstractCard c : cards)
            {
                final int damage = Math.max(0, c.costForTurn * 2);
                if (damage > 0)
                {
                    GameActions.Bottom.TakeDamageAtEndOfTurn(damage, AttackEffects.DARK);
                }
                c.setCostForTurn(0);
            }
        });
    }
}