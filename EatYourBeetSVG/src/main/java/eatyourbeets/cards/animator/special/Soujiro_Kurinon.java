package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.LogHorizon.Soujiro;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Soujiro_Kurinon extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Soujiro_Kurinon.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(Soujiro.DATA.Series);

    public Soujiro_Kurinon()
    {
        super(DATA);

        Initialize(0, 5, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_White(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ModifyAllCopies(Soujiro.DATA.ID)
        .AddCallback(info, (info2, c) ->
        {
            int numAttacksGain = 1;

            numAttacksGain += GetNumberSpecialColorlessInPile(player.hand);
            numAttacksGain += GetNumberSpecialColorlessInPile(player.discardPile);

            GameUtilities.IncreaseMagicNumber(c, numAttacksGain, false);
            c.flash();
        });
    }

    private int GetNumberSpecialColorlessInPile(CardGroup group) {
        int num = 0;

        for (AbstractCard card : group.group) {
            if (card.rarity == CardRarity.SPECIAL || card.color == CardColor.COLORLESS) {
                num++;
            }
        }

        return num;
    }
}