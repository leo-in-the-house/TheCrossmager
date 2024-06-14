package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Arboliva extends PokemonCard {
    public static final EYBCardData DATA = Register(Arboliva.class)
            .SetSkill(3, CardRarity.BASIC, EYBCardTarget.None);

    public Arboliva() {
        super(DATA);

        Initialize(0, 20, 10, 3);
        SetUpgrade(0, 1, 2);
        SetEvolved(true);
        SetAffinity_Green(1);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryHP(magicNumber);
        GameActions.Bottom.StackPower(new ArbolivaPower(player, secondaryValue));
    }

    public static class ArbolivaPower extends AnimatorPower
    {
        public ArbolivaPower(AbstractPlayer owner, int amount)
        {
            super(owner, Arboliva.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void atStartOfTurn()
        {
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

        public int onAttacked(DamageInfo info, int damageAmount)
        {
            GameActions.Top.GainTemporaryHP(amount);

            return damageAmount;
        }
    }
}