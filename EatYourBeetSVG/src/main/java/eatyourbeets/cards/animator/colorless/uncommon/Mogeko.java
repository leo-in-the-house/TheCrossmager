package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Mogeko extends AnimatorCard {
    public static final EYBCardData DATA = Register(Mogeko.class)
            .SetPower(3, CardRarity.UNCOMMON)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.MogekoCastle);

    public Mogeko() {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 5);

        SetAffinity_Pink(1);
        SetAffinity_Violet(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new MogekoPower(p, magicNumber));
    }

    public static class MogekoPower extends AnimatorPower {
        public MogekoPower(AbstractCreature owner, int amount) {
            super(owner, Mogeko.DATA);
            Initialize(amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            int numAttacks = 0;

            for (AbstractCard c : player.hand.group)
            {
                if (c.type == CardType.ATTACK)
                {
                    numAttacks++;
                }
            }

            if (numAttacks > 0) {
                int[] damageMatrix = DamageInfo.createDamageMatrix(amount * numAttacks, true);
                GameActions.Bottom.VFX(VFX.Whirlwind());
                GameEffects.List.Add(new DieDieDieEffect());
                GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE);
            }
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}