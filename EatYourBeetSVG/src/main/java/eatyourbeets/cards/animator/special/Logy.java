package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Atelier.EschaMalier;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Logy extends AnimatorCard {
    public static final EYBCardData DATA = Register(Logy.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.Atelier);
    static
    {
        DATA.AddPreview(new EschaMalier(), false);
    }

    public Logy() {
        super(DATA);

        Initialize(0, 12, 0);
        SetUpgrade(0, 8, 0);

        SetAffinity_Teal(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);


        AbstractCard escha = this.FindEscha();

        if (escha != null) {
            GameActions.Bottom.Draw(escha);
        }
    }

    private AbstractCard FindEscha()
    {

        for (AbstractCard c : player.discardPile.group)
        {
            if (EschaMalier.DATA.ID.equals(c.cardID)) {
                return c;
            }
        }
        return null;
    }
}