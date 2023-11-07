package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class HiiragiTenri extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(HiiragiTenri.class)
            .SetSkill(4, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.OwariNoSeraph);

    public HiiragiTenri()
    {
        super(DATA);

        Initialize(0, 0, 2, 0);
        SetCostUpgrade(-1);

        SetAffinity_Black(2);
        SetAffinity_Red(2);
        SetAffinity_Brown(2);

        SetPurge(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (AbstractCard c : p.exhaustPile.group)
        {
            if (GameUtilities.IsPlayable(c)) {
                GameActions.Bottom.PlayCard(c, p.exhaustPile, m);
                GameActions.Bottom.MoveCard(c, p.discardPile);
                GameActions.Bottom.GainTemporaryHP(magicNumber);
            }
        }
    }
}