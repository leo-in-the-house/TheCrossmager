package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class GinBankai extends AnimatorCard {
    public static final EYBCardData DATA = Register(GinBankai.class)
            .SetPower(-1, CardRarity.SPECIAL)
            .SetSeries(CardSeries.Bleach);

    public GinBankai() {
        super(DATA);

        Initialize(0, 0, 7);
        SetUpgrade(0, 0, 6);

        SetAffinity_Pink(2);
        SetAffinity_Green(1);
        SetAffinity_Red(1);

        SetMultiDamage(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);
        int stacks = GameUtilities.UseXCostEnergy(this);

        if (stacks > 0) {
            GameActions.Bottom.StackPower(new GinBankaiPower(p, magicNumber * stacks));
        }
    }

    public static class GinBankaiPower extends AnimatorPower {
        public GinBankaiPower(AbstractCreature owner, int amount) {
            super(owner, GinBankai.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            super.atEndOfTurn(isPlayer);

            int[] damageMatrix = DamageInfo.createDamageMatrix(amount, true);
            GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL)
                .SetDamageEffect((c, __) -> {
                    CardCrawlGame.sound.play("ATTACK_WHIRLWIND", 0.2f);
                    GameEffects.List.Add(new WhirlwindEffect());
                })
                .AddCallback(monsters -> {
                    if (monsters.size() == 1) {
                        GameActions.Bottom.ApplyPoison(TargetHelper.Enemies(), amount);
                    }
                });
        }
    }
}