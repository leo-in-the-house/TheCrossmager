package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Atelier.EschaMalier;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Logy extends AnimatorCard {
    public static final EYBCardData DATA = Register(Logy.class)
            .SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None)
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

        SetCardPreview(EschaMalier.DATA::IsCard).SetGroupType(player.discardPile.type);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.FetchFromPile(name, 1, player.discardPile)
                .SetFilter(card -> card.cardID.equals(EschaMalier.DATA.ID))
                .SetOptions(true, true);
    }
}