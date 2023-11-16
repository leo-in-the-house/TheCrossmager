package eatyourbeets.powers.replacement;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RetainCardPower;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;

public class TemporaryRetainPower extends RetainCardPower implements CloneablePowerInterface
{
    public TemporaryRetainPower(AbstractCreature owner, int numCards)
    {
        super(owner, numCards);

        this.ID = GR.Animator.CreateID(TemporaryRetainPower.class.getSimpleName());
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        super.atEndOfTurnPreEndTurnCards(isPlayer);

        if (isPlayer && !AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.player.hasRelic("Runic Pyramid") && !AbstractDungeon.player.hasPower("Equilibrium")) {
            this.addToBot(new RetainCardsAction(this.owner, this.amount));
        }

    }

    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        GameActions.Bottom.RemovePower(owner, owner, this);
    }

    @Override
    public AbstractPower makeCopy()
    {
        return new TemporaryRetainPower(owner, amount);
    }
}