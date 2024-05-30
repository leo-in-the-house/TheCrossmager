package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Frieren extends AnimatorCard {
    public static final EYBCardData DATA = Register(Frieren.class)
            .SetPower(3, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS);

    public Frieren() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetSeries(CardSeries.Vocaloid);

        SetAffinity_Brown(2);
        SetAffinity_Blue(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new FreirenPower(p, magicNumber));
    }

    public static class FreirenPower extends AnimatorPower {
        public FreirenPower(AbstractCreature owner, int amount) {
            super(owner, Frieren.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            if (amount > 0)
            {
                GameActions.Bottom.Draw(amount);
                flashWithoutSound();
            }
        }


        @Override
        public void onPlayCard(AbstractCard card, AbstractMonster m)
        {
            super.onPlayCard(card, m);

            if (!card.exhaust && !card.tags.contains(EYBCard.PURGE) && !card.purgeOnUse)
            {
                card.exhaust = true;
                GameActions.Top.GainEnergy(1);
                this.flashWithoutSound();
            }
        }

    }
}