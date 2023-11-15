package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Rigur;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Gobta extends AnimatorCard {
    public static final EYBCardData DATA = Register(Gobta.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Rigur(), true));

    public Gobta() {
        super(DATA);

        Initialize(0, 6, 0);
        SetUpgrade(0, 3, 0);

        SetExhaust(true);

        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.MakeCardInDrawPile(new Rigur())
                .SetUpgrade(upgraded, true);
    }
}