package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Illya_Miyu;
import eatyourbeets.cards.animator.special.Illya_Prisma;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RotatingList;

public class Illya extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Illya.class)
            .SetSkill(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Fate_PrismaIllya(Illya_Prisma.DATA));
                data.AddPreview(new Illya_Prisma(), false);
                data.AddPreview(new Illya_Miyu(), false);
            });

    public Illya()
    {
        super(DATA);

        Initialize(0, 0, 6);
        SetUpgrade(0, 0, -3);

        SetAffinity_White(1);
        SetAffinity_Blue(1);

        SetCardPreview(this::FindCards);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        final AbstractCard card = cardPreview.FindCard(m);
        if (card != null)
        {
            GameActions.Bottom.TakeDamageAtEndOfTurn(magicNumber);
            GameActions.Bottom.PlayCard(card, p.drawPile, m);
        }
    }

    private void FindCards(RotatingList<AbstractCard> cards, AbstractMonster target)
    {
        cards.Clear();
        AbstractCard bestCard = null;
        int maxDamage = Integer.MIN_VALUE;
        for (AbstractCard c : player.drawPile.group)
        {
            if (c.type == CardType.ATTACK && GameUtilities.IsPlayable(c, target) && !c.tags.contains(VOLATILE))
            {
                c.calculateCardDamage(target);
                if (c.damage > maxDamage)
                {
                    maxDamage = c.damage;
                    bestCard = c;
                }
                c.resetAttributes();
            }
        }
        cards.Add(bestCard);
    }
}