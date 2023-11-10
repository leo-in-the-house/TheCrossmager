package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SakuyaOhtori extends AnimatorCard {
    public static final EYBCardData DATA = Register(SakuyaOhtori.class)
            .SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.Rewrite);

    public SakuyaOhtori() {
        super(DATA);

        Initialize(0, 13, 0);
        SetUpgrade(0, 8, 0);

        SetAffinity_White(1, 0, 1);
        SetAffinity_Brown(1, 0, 1);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Top.PlayCopy(this, null);
        GameActions.Last.Exhaust(this);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChangeStance(CalmStance.STANCE_ID)
                .TriggerOnSameStance(true);
    }
}