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

public class Illya_Miyu extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Illya_Miyu.class)
            .SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeries(CardSeries.Fate);

    public Illya_Miyu()
    {
        super(DATA);

        Initialize(0, 3, 2);
        SetUpgrade(0, 4, 2);

        SetAffinity_Pink(1);
        SetAffinity_Black(1, 0, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.LIGHTNING);
        GameActions.Bottom.GainBlack(magicNumber);
        GameActions.Bottom.Callback(() -> {
            DrawIllya(player.drawPile);
        });
    }

    private boolean DrawIllya(CardGroup group)
    {
        for (AbstractCard c : group.group)
        {
            if (Illya_Prisma.DATA.ID.equals(c.cardID))
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
