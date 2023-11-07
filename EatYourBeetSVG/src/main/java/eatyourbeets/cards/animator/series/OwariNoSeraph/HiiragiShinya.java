package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class HiiragiShinya extends AnimatorCard
{
    public static final EYBCardData DATA = Register(HiiragiShinya.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public HiiragiShinya()
    {
        super(DATA);

        Initialize(0, 5, 2);
        SetUpgrade(0, 4, 0);

        SetAffinity_Pink(1, 0, 1);
        SetAffinity_Brown(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.FetchFromPile(name, 1, p.discardPile)
        .SetOptions(false, false)
        .AddCallback(cards ->
        {
            if (cards.size() > 0)
            {
                final AbstractCard card = cards.get(0);
                GameActions.Top.Motivate(card, 1);
                card.isEthereal = true;
                card.exhaust = true;
                GameUtilities.RefreshHandLayout();
            }
        });
    }
}