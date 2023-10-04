package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ganyu extends AnimatorCard {
    public static final EYBCardData DATA = Register(Ganyu.class)
            .SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.GenshinImpact);

    public Ganyu() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainOrbSlots(1);
        GameActions.Bottom.ChannelOrb(new Frost());
    }
}