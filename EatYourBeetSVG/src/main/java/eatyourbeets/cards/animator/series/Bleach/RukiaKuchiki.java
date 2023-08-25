package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.animator.special.RukiaBankai;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.utilities.GameActions;

public class RukiaKuchiki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RukiaKuchiki.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new RukiaBankai(), false);
    }

    public RukiaKuchiki()
    {
        super(DATA);

        Initialize(0, 0, 2, 0);
        SetUpgrade(0, 0, 2, 0);
        
        SetAffinity_Blue(1);
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        Frost frost = new Frost();
        frost.passiveAmount += magicNumber;
        GameActions.Bottom.ChannelOrb(frost);

        if (ForceStance.IsActive())
        {
            GameActions.Bottom.MakeCardInDrawPile(new RukiaKuchiki());
            GameActions.Last.ModifyAllInstances(uuid).AddCallback(GameActions.Bottom::Exhaust);
        }
    }
}