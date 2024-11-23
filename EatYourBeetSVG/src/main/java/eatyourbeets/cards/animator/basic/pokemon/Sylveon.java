package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Sylveon extends PokemonCard {
    public static final EYBCardData DATA = Register(Sylveon.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Sylveon() {
        super(DATA);

        Initialize(10, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_White(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.StackPower(new SylveonPower(p, 1));
    }


    public static class SylveonPower extends AnimatorPower
    {

        public SylveonPower(AbstractPlayer owner, int amount)
        {
            super(owner, Sylveon.DATA);

            this.amount = amount;

            updateDescription();
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
        public void atEndOfTurn(boolean isPlayer)
        {
            if (isPlayer)
            {
                flash();

                GameActions.Bottom.VFX(new RainbowCardEffect());
                GameActions.Bottom.Callback(() -> {
                   for (AbstractCard card : player.hand.group) {
                       GameUtilities.Retain(card);
                       GameActions.Top.IncreaseScaling(card, Affinity.White, amount);
                   }
                });
            }

            RemovePower();
        }
    }

}