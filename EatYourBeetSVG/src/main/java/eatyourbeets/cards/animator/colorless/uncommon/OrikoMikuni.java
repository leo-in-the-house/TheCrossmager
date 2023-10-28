package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
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

public class OrikoMikuni extends AnimatorCard {
    public static final EYBCardData DATA = Register(OrikoMikuni.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.MadokaMagica);

    public OrikoMikuni() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Pink(1);

        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new OrikoMikuniPower(p, 1));
    }

    public static class OrikoMikuniPower extends AnimatorPower {
        public OrikoMikuniPower(AbstractCreature owner, int amount) {
            super(owner, OrikoMikuni.DATA);
            Initialize(amount);
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            GameActions.Bottom.Scry(amount)
                .AddCallback(cards -> {
                    for (AbstractCard card : cards) {
                        if (GameUtilities.IsHindrance(card)) {
                            GameActions.Top.Exhaust(card);
                        }
                    }
                });
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}