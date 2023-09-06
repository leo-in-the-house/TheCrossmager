package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.RoseDamageAction;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;

public class Rose extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(Rose.class)
            .SetAttack(3, CardRarity.SPECIAL, EYBAttackType.Ranged)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Elsword);

    public Rose()
    {
        super(DATA);

        Initialize(7, 0, 2, 25);
        SetUpgrade(7, 0, 2, 0);

        SetAffinity_Red(2);
        SetAffinity_Green(2);
        SetAffinity_Blue(2);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Draw(magicNumber);
        GameActions.Bottom.Reload(name, m, (enemy, cards) ->
        {
            if (enemy != null && !GameUtilities.IsDeadOrEscaped(enemy))
            {
                GameActions.Bottom.GainRed(3);
                GameActions.Bottom.GainGreen(3);
                GameActions.Bottom.GainBlue(3);
                GameActions.Bottom.Add(new RoseDamageAction(enemy, this, cards.size() + 1, damage));
            }
        });
    }
}