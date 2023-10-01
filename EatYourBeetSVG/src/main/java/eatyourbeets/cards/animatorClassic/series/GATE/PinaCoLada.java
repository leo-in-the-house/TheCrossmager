package eatyourbeets.cards.animatorClassic.series.GATE;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class PinaCoLada extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(PinaCoLada.class).SetSeriesFromClassPackage().SetPower(2, CardRarity.RARE);

    public PinaCoLada()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 6, 0);

        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new PinaCoLadaPower(p, 1));
    }

    public static class PinaCoLadaPower extends AnimatorPower
    {
        public static final String POWER_ID = CreateFullID(eatyourbeets.cards.animator.series.GATE.PinaCoLada.PinaCoLadaPower.class);

        private int baseAmount;

        public PinaCoLadaPower(AbstractCreature owner, int amount)
        {
            super(owner, POWER_ID);

            Initialize(amount);
        }

        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            ResetAmount();
        }

        @Override
        public void onUseCard(AbstractCard card, UseCardAction action)
        {
            super.onUseCard(card, action);

            if ((card.freeToPlayOnce || card.costForTurn == 0) && amount > 0 && GameUtilities.CanPlayTwice(card))
            {
                GameActions.Top.PlayCopy(card, (AbstractMonster)((action.target == null) ? null : action.target));
                this.amount -= 1;
                updateDescription();
                flash();
            }
        }
    }
}