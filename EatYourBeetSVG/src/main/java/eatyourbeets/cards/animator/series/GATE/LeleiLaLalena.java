package eatyourbeets.cards.animator.series.GATE;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class LeleiLaLalena extends AnimatorCard
{
    public static final EYBCardData DATA = Register(LeleiLaLalena.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    public LeleiLaLalena()
    {
        super(DATA);

        Initialize(0, 0, 2);

        SetAffinity_Blue(1);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            return player.hand.getAttacks().size() > 0;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DiscardFromHand(name, 1, !upgraded)
                .ShowEffect(!upgraded, !upgraded)
                .SetFilter(card -> card.type == CardType.ATTACK)
                .SetOptions(false, false, false)
                .AddCallback(() -> GameActions.Bottom.ChannelOrbs(Frost::new, magicNumber));
    }
}