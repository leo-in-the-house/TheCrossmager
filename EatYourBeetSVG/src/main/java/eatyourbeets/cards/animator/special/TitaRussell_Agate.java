package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TitaRussell_Agate extends AnimatorCard {
    public static final EYBCardData DATA = Register(TitaRussell_Agate.class)
            .SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeries(CardSeries.TheLegendOfHeroesTrails);

    public TitaRussell_Agate() {
        super(DATA);

        Initialize(14, 0, 3);
        SetUpgrade(5, 0, 3);

        SetAffinity_Red(1);
        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);

        int numLightning = 0;

        for (AbstractOrb orb : player.orbs) {
            if (Lightning.ORB_ID.equals(orb.ID)) {
                numLightning++;
                break;
            }
        }

        if (numLightning > 0) {
            GameActions.Bottom.GainVigor(magicNumber * numLightning);
        }
    }
}