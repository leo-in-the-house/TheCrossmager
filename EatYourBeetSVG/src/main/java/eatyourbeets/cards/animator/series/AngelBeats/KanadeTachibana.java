package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.GameActions;

public class KanadeTachibana extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KanadeTachibana.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public KanadeTachibana()
    {
        super(DATA);

        Initialize(0, 0, 4, 0);
        SetUpgrade(0, 0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Light(1);
        SetAffinity_Blue(1);

        SetExhaust(true);
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Top.FetchFromPile(name, magicNumber, p.discardPile)
        .SetOptions(false, true)
        .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
        .AddCallback(cards -> {
            for (AbstractCard card : cards) {
                card.isEthereal = true;
                CostModifiers.For(card).Add(-1);
            }
        });
    }
}