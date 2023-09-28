package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Plachta extends AnimatorCard {
    public static final EYBCardData DATA = Register(Plachta.class)
            .SetPower(1, CardRarity.SPECIAL)
            .SetSeries(CardSeries.Atelier);

    public Plachta() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new PlachtaPower(p, 1));
    }

    public static class PlachtaPower extends AnimatorPower {
        public PlachtaPower(AbstractCreature owner, int amount) {
            super(owner, Plachta.DATA);
            this.amount = amount;
            updateDescription();
        }
    }
}