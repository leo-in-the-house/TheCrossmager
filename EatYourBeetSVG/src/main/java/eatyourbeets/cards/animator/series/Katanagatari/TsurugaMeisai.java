package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;

public class TsurugaMeisai extends AnimatorCard
{
    public static final EYBCardData DATA = Register(TsurugaMeisai.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public TsurugaMeisai()
    {
        super(DATA);

        Initialize(0, 0);
        SetUpgrade(0, 0);

        SetAffinity_Black(1);

        SetExhaust(true);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.SelectFromHand(name, 1, false)
        .SetFilter(card -> (upgraded || GameUtilities.IsLowCost(card)) && card.type == CardType.ATTACK)
        .SetOptions(true, true, true)
        .SetMessage(GR.Common.Strings.HandSelection.Copy)
        .AddCallback(cards ->
        {
            for (AbstractCard c : cards)
            {
                GameActions.Top.MakeCardInHand(GameUtilities.Imitate(c));
                GameActions.Top.SealAffinities(c);
            }
        });
    }
}