package eatyourbeets.cards.animatorClassic.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;

public class Rose extends AnimatorClassicCard_UltraRare
{
    public static final EYBCardData DATA = Register(Rose.class).SetAttack(3, CardRarity.SPECIAL, EYBAttackType.Ranged).SetColor(CardColor.COLORLESS);

    public Rose()
    {
        super(DATA);

        Initialize(10, 0, 2, 40);
        SetUpgrade(0, 0, 1, 0);

        this.series = CardSeries.Elsword;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
//        GameActions.Bottom.Draw(magicNumber);
//        GameActions.Bottom.Reload(name, m, (enemy, cards) ->
//        {
//            if (cards.size() > 0 && enemy != null && !GameUtilities.IsDeadOrEscaped(enemy))
//            {
//                GameActions.Bottom.Add(new RoseDamageAction(enemy, this, cards.size(), damage));
//            }
//        });
    }
}