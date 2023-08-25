package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SajinKomamura extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SajinKomamura.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public SajinKomamura()
    {
        super(DATA);

        Initialize(0, 0, 90,0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Red(1);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            double current = ((double) GameUtilities.GetHP(player, false, false) / GameUtilities.GetMaxHP(player, false, false));
            double threshold = (double) magicNumber / 100;
            return current < threshold;
        }

        return false;
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.ChangeStance(ForceStance.STANCE_ID);
    }
}