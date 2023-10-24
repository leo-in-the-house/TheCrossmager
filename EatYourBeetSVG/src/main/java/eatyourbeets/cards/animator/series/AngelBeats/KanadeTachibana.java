package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
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

        SetAffinity_White(1);
        SetAffinity_Blue(1);
        SetAffinity_Violet(1);

        SetDelayed(true);
        SetExhaust(true);
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.FetchFromPile(name, magicNumber, p.exhaustPile)
        .SetFilter(card -> card.isEthereal)
        .SetOptions(false, true)
        .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
        .AddCallback(cards -> {
            for (AbstractCard card : cards) {
                CostModifiers.For(card).Add(-1);
            }
        });
    }
}