package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class HornSkuld extends AnimatorCard {
    public static final EYBCardData DATA = Register(HornSkuld.class)
            .SetPower(4, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.OwariNoSeraph);

    public HornSkuld() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new HornSkuldPower(p, 1));
    }

    public static class HornSkuldPower extends AnimatorPower {
        public HornSkuldPower(AbstractCreature owner, int amount) {
            super(owner, HornSkuld.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target)
        {
            super.onAttack(info, damageAmount, target);

            if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL)
            {
                int unblockedDamage = damageAmount - target.currentBlock;

                if (unblockedDamage > 0) {
                    GameActions.Top.GainTemporaryHP(unblockedDamage);
                    this.flash();
                }
            }
        }
    }
}