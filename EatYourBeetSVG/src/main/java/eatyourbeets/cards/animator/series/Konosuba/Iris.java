package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.stances.DivinityStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Iris extends AnimatorCard {
    public static final EYBCardData DATA = Register(Iris.class).SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Iris() {
        super(DATA);

        Initialize(0, 5, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_White(1, 0, 1);
        SetAffinity_Yellow(1, 0, 1);

        SetAffinityRequirement(Affinity.White, 9);
        SetAffinityRequirement(Affinity.Yellow, 9);

        SetRetain(true);
        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetInnate(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChannelOrbs(Lightning::new, magicNumber);

        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.ChangeStance(DivinityStance.STANCE_ID);
        }
    }
}