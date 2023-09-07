package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Fate.MatouShinji;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MatouShinji_CommandSpell extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MatouShinji_CommandSpell.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(MatouShinji.DATA.Series);

    public MatouShinji_CommandSpell()
    {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Blue(1);
        SetAffinity_Black(1);
        SetCostUpgrade(-1);

        SetRetain(true);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        if (p.maxOrbs > 0)
        {
            GameActions.Bottom.FetchFromPile(name, 1, p.discardPile)
            .SetOptions(false, true)
            .SetFilter(c ->
            {
                final EYBCardAffinities a = GameUtilities.GetAffinities(c);
                return a != null && (a.GetLevel(Affinity.Sealed) > 0);
            })
            .AddCallback(cards ->
            {
                for (AbstractCard c : cards)
                {
                    GameActions.Bottom.IncreaseScaling(c, Affinity.White, c.costForTurn);
                    GameActions.Bottom.IncreaseScaling(c, Affinity.Black, c.costForTurn);
                    GameActions.Bottom.Motivate(c, 1);
                }
            });
        }
    }
}
