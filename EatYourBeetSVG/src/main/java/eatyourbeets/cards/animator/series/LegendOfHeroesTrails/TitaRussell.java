package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.animator.special.TitaRussell_Agate;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TitaRussell extends AnimatorCard {
    public static final EYBCardData DATA = Register(TitaRussell.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.LegendOfHeroesTrails_Agate(TitaRussell_Agate.DATA));
                data.AddPreview(new TitaRussell_Agate(), true);
            });

    public TitaRussell() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Yellow(1);

        SetLoyal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        if (GameUtilities.HasOrb(Lightning.ORB_ID)) {
            AbstractOrb lightning = null;

            for (AbstractOrb orb : player.orbs) {
                if (Lightning.ORB_ID.equals(orb.ID)) {
                    lightning = orb;
                    break;
                }
            }

            if (lightning != null) {
                GameActions.Bottom.EvokeOrb(1, lightning)
                        .SetDuration(Settings.ACTION_DUR_XFAST, true);
                GameActions.Bottom.ChannelOrb(lightning);
            }
        }
        else {
            GameActions.Bottom.ChannelOrb(new Lightning());
        }
    }
}