package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SSS_Elie extends AnimatorCard {
    public static final EYBCardData DATA = Register(SSS_Elie.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public SSS_Elie() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 2);

        SetAffinity_Yellow(1);

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (AbstractOrb orb : player.orbs) {
            if (Lightning.ORB_ID.equals(orb.ID)) {
                GameActions.Bottom.GainEnergy(1);
                if (upgraded) {
                    GameActions.Bottom.GainTemporaryHP(magicNumber);
                }
                GameActions.Bottom.RemoveOrb(orb);
            }
        }
    }
}