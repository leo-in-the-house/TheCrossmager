package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Obstagoon extends PokemonCard {
    public static final EYBCardData DATA = Register(Obstagoon.class)
            .SetSkill(3, CardRarity.BASIC, EYBCardTarget.None);

    public Obstagoon() {
        super(DATA);

        Initialize(0, 29, 3);
        SetUpgrade(0, 3, 0);

        SetAffinity_Violet(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new ObstagoonPower(player, magicNumber));
    }

    public static class ObstagoonPower extends AnimatorPower
    {
        public ObstagoonPower(AbstractPlayer owner, int amount)
        {
            super(owner, Obstagoon.DATA);

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
            if (damageAmount < player.currentBlock && info.owner != null) {
                GameActions.Top.ApplyVulnerable(TargetHelper.Normal(info.owner), amount);
                GameActions.Top.ApplyLockOn(TargetHelper.Normal(info.owner), amount);
            }

            return super.onAttacked(info, damageAmount);
        }
    }
}