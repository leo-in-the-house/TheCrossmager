package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Marielle extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Marielle.class)
            .SetSkill(3, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Marielle()
    {
        super(DATA);

        Initialize(0, 0, 12);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Green(1);
        SetAffinity_Blue(1);
        SetAffinity_White(1);

        SetDelayed(true);
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

        GameActions.Bottom.FetchFromPile(name, 1, p.discardPile)
        .SetOptions(false, false)
        .SetFilter(c -> c.hasTag(DELAYED))
        .AddCallback(cards -> {
           for (AbstractCard card : cards) {
               GameUtilities.ModifyCostForCombat(card, 0, false);
           }
        });
    }
}