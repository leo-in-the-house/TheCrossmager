package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Shirosaki_Laplace;
import eatyourbeets.cards.animator.status.Status_Dazed;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shirosaki extends AnimatorCard {
    public static final EYBCardData DATA = Register(Shirosaki.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Shirosaki_Laplace(), true);
                data.AddPreview(new Status_Dazed(), false);
            });

    public Shirosaki() {
        super(DATA);

        Initialize(0, 8, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_Pink(1);

        SetRetain(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.MakeCardInDrawPile(new Status_Dazed())
                .SetDestination(CardSelection.Top);
        GameActions.Bottom.MakeCardInDrawPile(new Shirosaki_Laplace())
                .SetDestination(CardSelection.Top)
                .SetUpgrade(upgraded, true);
    }
}