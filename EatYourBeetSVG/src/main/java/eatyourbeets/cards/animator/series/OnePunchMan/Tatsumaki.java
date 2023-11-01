package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.orbs.animator.Aether;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Tatsumaki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Tatsumaki.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Tatsumaki()
    {
        super(DATA);

        Initialize(0, 2, 1);
        SetUpgrade(0, 4, 1);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Pink(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ChannelOrb(new Aether());
        GameActions.Bottom.ChangeStance(MagicStance.STANCE_ID);
    }
}