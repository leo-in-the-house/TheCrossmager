package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RioSonogami extends AnimatorCard {
    public static final EYBCardData DATA = Register(RioSonogami.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public RioSonogami() {
        super(DATA);

        Initialize(0, 8, 8);
        SetUpgrade(0, 4, 4);

        SetAffinity_Teal(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.StackPower(new NextTurnBlockPower(player, magicNumber));
    }
}