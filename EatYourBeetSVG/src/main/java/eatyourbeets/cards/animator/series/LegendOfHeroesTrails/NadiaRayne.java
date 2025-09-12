package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class NadiaRayne extends AnimatorCard {
    public static final EYBCardData DATA = Register(NadiaRayne.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public NadiaRayne() {
        super(DATA);

        Initialize(0, 3, 2, 2);
        SetUpgrade(0, 2, 0);

        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            if (GameUtilities.GetCommonDebuffs(TargetHelper.Normal(enemy)).size() > 0) {
                GameActions.Bottom.ChannelOrbs(Lightning::new, secondaryValue);
                break;
            }
        }
    }
}