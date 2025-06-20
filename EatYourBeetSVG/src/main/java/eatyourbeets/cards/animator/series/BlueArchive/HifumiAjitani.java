package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class HifumiAjitani extends AnimatorCard {
    public static final EYBCardData DATA = Register(HifumiAjitani.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public HifumiAjitani() {
        super(DATA);

        Initialize(0, 8, 3);
        SetUpgrade(0, 2, 0);

        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.GainEnergy(1);

            if (upgraded) {
                GameActions.Bottom.Draw(1);
            }
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        int numEnemies = GameUtilities.GetEnemies(true).size();

        return numEnemies >= magicNumber;
    }
}