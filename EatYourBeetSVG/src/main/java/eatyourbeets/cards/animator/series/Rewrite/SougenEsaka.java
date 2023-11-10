package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SougenEsaka extends AnimatorCard {
    public static final EYBCardData DATA = Register(SougenEsaka.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public SougenEsaka() {
        super(DATA);

        Initialize(0, 4, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Brown(1, 0, 1);
        SetAffinity_Yellow(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        if (GameUtilities.InGame()) {
            return super.GetBlockInfo().AddMultiplier(!GameUtilities.InStance(NeutralStance.STANCE_ID) ? 2 : 1);
        }

        return super.GetBlockInfo().AddMultiplier(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (!GameUtilities.InStance(NeutralStance.STANCE_ID)) {
            GameActions.Bottom.GainBlock(block);
        }

        if (GameUtilities.InStance(CalmStance.STANCE_ID)) {
            GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
        }
        else {
            GameActions.Bottom.ChangeStance(CalmStance.STANCE_ID);
        }
    }
}