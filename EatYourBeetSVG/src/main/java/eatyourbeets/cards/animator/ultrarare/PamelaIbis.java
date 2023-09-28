package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class PamelaIbis extends AnimatorCard {
    public static final EYBCardData DATA = Register(PamelaIbis.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public PamelaIbis() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new PamelaIbisPower(p, 1));
    }

    public static class PamelaIbisPower extends AnimatorPower {
        public PamelaIbisPower(AbstractCreature owner, int amount) {
            super(owner, PamelaIbis.DATA);
            this.amount = amount;
            updateDescription();
        }
    }
}