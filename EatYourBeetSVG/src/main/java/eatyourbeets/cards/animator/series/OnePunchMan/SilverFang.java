package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SilverFang extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SilverFang.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeries(CardSeries.OnePunchMan);

    public SilverFang()
    {
        super(DATA);

        Initialize(0, 10, 0);
        SetUpgrade(0, 8, 0);

        SetAffinity_Green(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        AbstractCard last = GameUtilities.GetLastCardPlayed(this, true);

        return last != null && GameUtilities.HasAnyScaling(last);
    }
}