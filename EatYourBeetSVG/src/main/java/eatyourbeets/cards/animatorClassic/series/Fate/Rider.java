package eatyourbeets.cards.animatorClassic.series.Fate;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.HashSet;

public class Rider extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Rider.class).SetSeriesFromClassPackage().SetSkill(2, CardRarity.COMMON);

    private static final HashSet<CardTags> tagCache = new HashSet<>();

    public Rider()
    {
        super(DATA);

        Initialize(0, 6, 3);
        SetUpgrade(0, 0, 1);
        SetScaling(0, 1, 0);


    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        if (m != null)
        {
            GameUtilities.GetIntent(m).AddStrength(-magicNumber);
        }
    }

    @Override
    protected void OnUpgrade()
    {
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ReduceStrength(m, magicNumber, true);
        GameActions.Bottom.Callback(() ->
        {
            tagCache.clear();
            for(AbstractCard c : player.hand.group)
            {
                tagCache.addAll(c.tags);
            }
            if (tagCache.contains(MARTIAL_ARTIST) && tagCache.contains(SPELLCASTER))
            {
                GameActions.Bottom.GainGreen(1);
                GameActions.Bottom.GainBlue(1);
            }
        });
    }
}