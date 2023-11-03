package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.LogHorizon.Soujiro;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Soujiro_Nazuna extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Soujiro_Nazuna.class)
            .SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(Soujiro.DATA.Series);

    public Soujiro_Nazuna()
    {
        super(DATA);

        Initialize(0, 0, 6);
        SetUpgrade(0, 0, 6);

        SetAffinity_Yellow(2);

        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainTemporaryHP(magicNumber);

        GameActions.Bottom.Callback(() -> {
            MakeDelayedCardsZeroCost(player.drawPile);
            MakeDelayedCardsZeroCost(player.discardPile);
            MakeDelayedCardsZeroCost(player.hand);
        });
    }

    private void MakeDelayedCardsZeroCost(CardGroup group) {
        for (AbstractCard card : group.group) {
            if (card instanceof EYBCard && ((EYBCard) card).hasTag(DELAYED) && card.cost > 0) {
                GameUtilities.ModifyCostForCombat(card, 0, false);
            }
        }
    }
}
