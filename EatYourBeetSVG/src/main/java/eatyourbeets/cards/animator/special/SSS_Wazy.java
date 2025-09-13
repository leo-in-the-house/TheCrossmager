package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SSS_Wazy extends AnimatorCard {
    public static final EYBCardData DATA = Register(SSS_Wazy.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public SSS_Wazy() {
        super(DATA);

        Initialize(0, 4, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Blue(1, 0, 1);

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (CalmStance.IsActive()) {
            GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID);
        }
        else {
            GameActions.Bottom.ChangeStance(CalmStance.STANCE_ID);
        }
    }
}