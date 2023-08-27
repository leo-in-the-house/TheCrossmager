package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Zadkiel extends AnimatorCard {
    public static final EYBCardData DATA = Register(Zadkiel.class)
            .SetSkill(2, CardRarity.SPECIAL)
            .SetSeries(CardSeries.DateALive);

    public Zadkiel() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Green(2);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {

        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainOrbSlots(magicNumber);
        GameActions.Bottom.ChannelOrbs(Frost::new, GameUtilities.GetOrbCount(EmptyOrbSlot.ORB_ID));
    }
}