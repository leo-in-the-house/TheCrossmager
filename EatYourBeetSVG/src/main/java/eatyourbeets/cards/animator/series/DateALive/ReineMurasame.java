package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.modifiers.BlockModifiers;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ReineMurasame extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ReineMurasame.class)
            .SetSkill(-1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new eatyourbeets.cards.animator.series.DateALive.ShidoItsuka(), true);
    }

    public ReineMurasame()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetExhaust(true);

        SetAffinity_Pink(2);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        int stacks = GameUtilities.UseXCostEnergy(this);

        if (stacks > 0) {
            GameActions.Bottom.StackPower(new ReineMurasamePower(player, stacks, magicNumber));
        }
    }

    public static class ReineMurasamePower extends AnimatorPower {

        private final int blockMultiplier;
        public ReineMurasamePower(AbstractPlayer owner, int amount, int multiplier) {
            super(owner, ReineMurasame.DATA);

            this.blockMultiplier = multiplier;

            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount, blockMultiplier);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            GameActions.Bottom.Retain(name, amount, false)
                 .SetOptions(true, true, true)
                 .AddCallback(cards -> {
                     for (AbstractCard card : cards) {
                         if (card.block > 0) {
                             BlockModifiers.For(card).Add(card.block * (blockMultiplier - 1));
                         }
                     }
                 });

            RemovePower();
        }


    }
}