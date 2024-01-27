package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.colorless.rare.TanyaDegurechaff;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TanyaDegurechaff_Type95 extends AnimatorCard
{
    public static final EYBCardData DATA = Register(TanyaDegurechaff_Type95.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(TanyaDegurechaff.DATA.Series);

    public TanyaDegurechaff_Type95()
    {
        super(DATA);

        Initialize(0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Red(1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        int affinityLevel = CombatStats.Affinities.GetAffinityLevel(Affinity.Red);

        if (affinityLevel > 9) {
            affinityLevel = 9;
        }

        if (affinityLevel > 0) {
            GameUtilities.PlayVoiceSFX(name);

            int curAffinityLevel = affinityLevel;
            GameActions.Bottom.SelectFromHand(name, 1, false)
            .SetFilter(c -> c instanceof EYBCard && ((EYBCard) c).CanScale())
            .AddCallback(cards ->
            {
                for (AbstractCard c : cards)
                {
                    GameActions.Top.IncreaseScaling(c, Affinity.Red, curAffinityLevel);
                }
            });
        }
    }
}