package eatyourbeets.cards.animator.colorless.rare;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class AyakaKamisato extends AnimatorCard {
    public static final EYBCardData DATA = Register(AyakaKamisato.class)
            .SetAttack(2, CardRarity.RARE, EYBAttackType.Piercing)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public AyakaKamisato() {
        super(DATA);

        Initialize(9, 0, 3);
        SetUpgrade(2, 0, 2);

        SetAffinity_Blue(1);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.ICE);

        GameActions.Bottom.StackPower(new AyakaKamisatoPower(p, 1));

        Frost frost = new Frost();
        Water water = new Water();

        GameActions.Bottom.ChannelOrb(frost);
        GameActions.Bottom.ChannelOrb(water);

        GameActions.Bottom.TriggerOrbPassive(frost, magicNumber);
        GameActions.Bottom.TriggerOrbPassive(water, magicNumber);
    }

    public static class AyakaKamisatoPower extends AnimatorPower {
        public AyakaKamisatoPower(AbstractCreature owner, int amount) {
            super(owner, AyakaKamisato.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            RemovePower();
        }

        @Override
        public void onGainedBlock(float blockAmount) {
            ConvertBlockToFreeze(MathUtils.floor(blockAmount));
        }

        @Override
        public int onPlayerGainedBlock(float blockAmount)
        {
            ConvertBlockToFreeze(MathUtils.floor(blockAmount));
            return super.onPlayerGainedBlock(blockAmount);
        }

        @Override
        public int onPlayerGainedBlock(int blockAmount)
        {
            ConvertBlockToFreeze(blockAmount);
            return super.onPlayerGainedBlock(blockAmount);
        }

        private void ConvertBlockToFreeze(int amount) {
            if (amount > 0) {
                GameActions.Bottom.LoseBlock(amount);
                GameActions.Bottom.ApplyFreezing(TargetHelper.Enemies(), amount);
            }
        }
    }
}