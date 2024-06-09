package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
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

public class Pyra extends AnimatorCard {
    public static final EYBCardData DATA = Register(Pyra.class)
            .SetPower(0, CardRarity.SPECIAL)
            .SetSeries(CardSeries.XenobladeChronicles);

    public Pyra() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetRetain(true);

        SetAffinity_Red(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        if (upgraded) {
            GameActions.Bottom.StackPower(new PyraPower(p, 2));
        }
        else {
            GameActions.Bottom.StackPower(new PyraPower(p, 1));
        }
    }

    public static class PyraPower extends AnimatorPower {
        boolean skillPlayed = false;
        public PyraPower(AbstractCreature owner, int amount) {
            super(owner, Pyra.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            skillPlayed = false;
        }

        @Override
        public void onUseCard(AbstractCard card, UseCardAction action)
        {
            super.onUseCard(card, action);

            if (!skillPlayed && card.type == CardType.SKILL && card.block > 0) {
                GameActions.Bottom.GainFlameBarrier(card.block * amount);
                skillPlayed = true;
            }
        }
    }
}