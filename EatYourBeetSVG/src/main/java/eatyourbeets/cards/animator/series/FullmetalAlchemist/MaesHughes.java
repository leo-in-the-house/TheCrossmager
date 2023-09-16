package eatyourbeets.cards.animator.series.FullmetalAlchemist;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.List;

public class MaesHughes extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MaesHughes.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public MaesHughes()
    {
        super(DATA);

        Initialize(0, 8, 0);
        SetUpgrade(0, 4, 0);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        AbstractCard topMatchingCard = this.FindTopMatchingCard();

        if (topMatchingCard != null) {
            GameActions.Bottom.Draw(topMatchingCard);
        }
    }

    private AbstractCard FindTopMatchingCard()
    {
        AbstractCard bestCard = null;

        List<String> stringsToParse = GameUtilities.GetAllOrbShortcuts();

        stringsToParse.add("Orb");
        stringsToParse.add("Orb Core");

        for (AbstractCard c : player.drawPile.group)
        {
            if (GameUtilities.DescriptionContainsIcon(c, stringsToParse.toArray(new String[0])))
            {
                bestCard = c;
            }
        }
        return bestCard;
    }
}