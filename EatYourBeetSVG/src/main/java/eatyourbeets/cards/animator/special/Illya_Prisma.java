package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Illya_Prisma extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Illya_Prisma.class)
            .SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeries(CardSeries.Fate);
    static
    {
        DATA.AddPreview(new Illya_Miyu(), false);
    }

    public Illya_Prisma()
    {
        super(DATA);

        Initialize(3, 0, 2);
        SetUpgrade(4, 0, 2);

        SetAffinity_Pink(1);
        SetAffinity_White(1, 0, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.LIGHTNING);
        GameActions.Bottom.GainWhite(magicNumber);
        GameActions.Bottom.Callback(() -> {
            DrawMiyu(player.drawPile);
        });
    }

    private boolean DrawMiyu(CardGroup group)
    {
        for (AbstractCard c : group.group)
        {
            if (Illya_Miyu.DATA.ID.equals(c.cardID))
            {
                if (group.type != CardGroup.CardGroupType.HAND)
                {
                    GameEffects.List.ShowCardBriefly(makeStatEquivalentCopy());
                    GameActions.Top.MoveCard(c, group, player.hand)
                            .ShowEffect(true, true)
                            .AddCallback(card -> {
                                card.setCostForTurn(0);
                            });
                }

                return true;
            }
        }

        return false;
    }

}
