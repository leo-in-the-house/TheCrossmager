package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class NiaHonjou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(NiaHonjou.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public NiaHonjou()
    {
        super(DATA);

        Initialize(0, 0);

        SetAffinity_Pink(1);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetExhaust(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new NiaHonjouPower(p, 1));
    }

    public static class NiaHonjouPower extends AnimatorPower
    {
        public NiaHonjouPower(AbstractPlayer owner, int amount)
        {
            super(owner, NiaHonjou.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);
            RemovePower();
        }

        @Override
        public void stackPower(int stackAmount)
        {
            super.stackPower(stackAmount);
            updateDescription();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onAfterCardPlayed(AbstractCard usedCard)
        {
            super.onAfterCardPlayed(usedCard);

            if (usedCard.block > 0) {
                GameActions.Bottom.Draw(amount);
            }
        }
    }
}