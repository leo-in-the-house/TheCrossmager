package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ResnaSternlicht extends AnimatorCard {
    public static final EYBCardData DATA = Register(ResnaSternlicht.class)
            .SetPower(1, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeriesFromClassPackage();

    public ResnaSternlicht() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new ResnaSternlichtPower(p, 1));
    }

    public static class ResnaSternlichtPower extends AnimatorPower {
        public ResnaSternlichtPower(AbstractCreature owner, int amount) {
            super(owner, ResnaSternlicht.DATA);
            this.amount = amount;
            updateDescription();
        }
    }
}