package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AltinaOrion extends AnimatorCard {
    public static final EYBCardData DATA = Register(AltinaOrion.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public AltinaOrion() {
        super(DATA);

        Initialize(0, 6, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_Teal(1);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            int curMode = 0;
            AbstractOrb previousOrb = null;

            for (AbstractOrb orb : player.orbs) {
                if (orb.ID == null) {
                    curMode = 0;
                    continue;
                }

                if (previousOrb == null || !orb.ID.equals(previousOrb.ID)) {
                    previousOrb = orb;
                    curMode = 0;
                }

                curMode++;
                if (curMode >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}