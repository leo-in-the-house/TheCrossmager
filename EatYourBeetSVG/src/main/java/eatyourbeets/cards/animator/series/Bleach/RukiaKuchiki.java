package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.animator.special.RukiaBankai;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RukiaKuchiki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RukiaKuchiki.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new RukiaBankai(), true);
    }

    public RukiaKuchiki()
    {
        super(DATA);

        Initialize(0, 0, 3, 0);
        SetUpgrade(0, 0, 3, 0);
        
        SetAffinity_Blue(1);
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        Frost frost = new Frost();
        GameUtilities.IncreaseOrbPassiveAmount(frost, magicNumber);
        GameActions.Bottom.ChannelOrb(frost);

        if (WrathStance.IsActive())
        {
            GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID);
            GameActions.Bottom.MakeCardInDrawPile(new RukiaBankai())
                    .SetUpgrade(upgraded, true);
            GameActions.Last.ModifyAllInstances(uuid).AddCallback(GameActions.Bottom::Exhaust);
        }
    }
}