package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Plasma;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Traveler_Lumine extends AnimatorCard_UltraRare {
    public static final EYBCardData DATA = Register(Traveler_Lumine.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public Traveler_Lumine() {
        super(DATA);

        Initialize(0, 8, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(2, 0, 2);
        SetAffinity_Yellow(2, 0, 2);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        if (CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.ChannelOrb(new Plasma());
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.GainOrbSlots(1);
        GameActions.Bottom.GainFocus(1);
    }
}