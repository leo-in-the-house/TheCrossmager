package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnCardCreatedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class PamelaIbis extends AnimatorCard_UltraRare implements OnCardCreatedSubscriber {
    public static final EYBCardData DATA = Register(PamelaIbis.class)
            .SetPower(1, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Atelier);

    public PamelaIbis() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Black(2);
    }


    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onCardCreated.Subscribe(this);
    }

    @Override
    public void OnCardCreated(AbstractCard card, boolean startOfBattle) {
        GameUtilities.IncreaseMagicNumber(this, 1, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainIntangible(magicNumber);

        GameActions.Bottom.StackPower(new PamelaIbisPower(p, 1));
    }

    public static class PamelaIbisPower extends AnimatorPower {
        public PamelaIbisPower(AbstractCreature owner, int amount) {
            super(owner, PamelaIbis.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            super.atEndOfTurn(isPlayer);

            GameActions.Bottom.GainDexterity(-amount);
        }
    }
}