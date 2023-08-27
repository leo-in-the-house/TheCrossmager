package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MukuroHoshimiya extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MukuroHoshimiya.class)
            .SetSkill(1, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public MukuroHoshimiya()
    {
        super(DATA);

        Initialize(0, 0, 6, 4);
        SetUpgrade(0,0,0, 2);

        SetAffinity_Light(2);
        SetRetain(true);
        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetInnate(true);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            return player.currentBlock >= magicNumber;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (AbstractCard card : player.hand.group) {
            GameActions.Bottom.SealAffinities(card, true);
        }

        GameActions.Bottom.Draw(secondaryValue);
    }
}