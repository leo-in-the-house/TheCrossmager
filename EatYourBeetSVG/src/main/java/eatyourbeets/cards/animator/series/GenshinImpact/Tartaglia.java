package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Tartaglia extends AnimatorCard {
    public static final EYBCardData DATA = Register(Tartaglia.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Tartaglia() {
        super(DATA);

        Initialize(0, 6, 3, 4);
        SetUpgrade(0, 3, -1);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (GameUtilities.GetUniqueOrbsCount() >= magicNumber) {
            GameActions.Bottom.ChangeStance(MagicStance.STANCE_ID);
            GameActions.Last.Exhaust(this);
        }
    }


    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Bottom.ApplyLockOn(TargetHelper.RandomEnemy(), secondaryValue);
    }
}