package eatyourbeets.cards.animator.series.BlueArchive;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.vfx.combat.LaserBeamEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.VFX;
import eatyourbeets.effects.vfx.megacritCopy.SmallLaserEffect2;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class HinaSorasaki extends AnimatorCard {
    public static final EYBCardData DATA = Register(HinaSorasaki.class)
            .SetPower(4, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public HinaSorasaki() {
        super(DATA);

        Initialize(0, 0, 8);
        SetUpgrade(0, 0, 8);

        SetAffinity_Pink(2);
        SetAffinity_White(1);
        SetAffinity_Black(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ApplyLockOn(TargetHelper.Enemies(), 1);
        GameActions.Bottom.StackPower(new HinaSorasakiPower(p, magicNumber));
    }

    public static class HinaSorasakiPower extends AnimatorPower {
        public HinaSorasakiPower(AbstractCreature owner, int amount) {
            super(owner, HinaSorasaki.DATA);
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

            for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(enemy), 1)
                        .AddCallback(power -> {
                            int lockOnAmount = GameUtilities.GetPowerAmount(enemy, LockOnPower.POWER_ID);

                            int damageAmount = lockOnAmount * amount;

                            if (damageAmount > 0) {
                                if (lockOnAmount > 10) {
                                    GameActions.Bottom.VFX(new LaserBeamEffect(player.hb.cX, player.hb.cY), 0.1f);
                                    GameActions.Bottom.VFX(new ExplosionSmallEffect(enemy.hb.cX + MathUtils.random(-0.05f, 0.05f), enemy.hb.cY + MathUtils.random(-0.05f, 0.05f)), 0.1f);
                                    GameActions.Bottom.VFX(new ExplosionSmallEffect(enemy.hb.cX + MathUtils.random(-0.05f, 0.05f), enemy.hb.cY + MathUtils.random(-0.05f, 0.05f)), 0.1f);
                                } else {
                                    GameActions.Bottom.VFX(new SmallLaserEffect2(player.hb.cX, player.hb.cY, VFX.RandomX(enemy.hb, 0.2f), VFX.RandomY(enemy.hb, 0.2f)), 0.1f);
                                    GameActions.Bottom.VFX(new ExplosionSmallEffect(enemy.hb.cX + MathUtils.random(-0.05f, 0.05f), enemy.hb.cY + MathUtils.random(-0.05f, 0.05f)), 0.1f);
                                }

                                GameActions.Bottom.DealDamage(player, enemy, damageAmount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE);
                            }
                        });
            }
        }
    }
}