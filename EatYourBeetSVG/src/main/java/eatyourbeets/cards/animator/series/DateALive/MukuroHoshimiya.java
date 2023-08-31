package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MukuroHoshimiya extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MukuroHoshimiya.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public MukuroHoshimiya()
    {
        super(DATA);

        Initialize(0, 0, 6, 2);
        SetUpgrade(0,0,0, 2);

        SetAffinity_Yellow(1);
        SetAffinity_Teal(1);
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

        GameActions.Bottom.Draw(secondaryValue)
        .AddCallback(c -> {
            for (AbstractCard card : player.hand.group) {
                GameActions.Top.SealAffinities(card, false);
            }
        });

    }
}