package eatyourbeets.actions.animator;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import eatyourbeets.actions.EYBAction;
import eatyourbeets.actions.damage.DealDamage;
import eatyourbeets.cards.animator.ultrarare.Rose;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RoseDamageAction extends EYBAction
{
    private final int damage;
    private final int times;
    private final Rose rose;

    private AbstractGameAction action;

    public RoseDamageAction(AbstractMonster enemy, Rose rose, int times, int damage)
    {
        super(ActionType.DAMAGE, Settings.ACTION_DUR_FAST);

        this.damage = damage;
        this.rose = rose;
        this.times = times;

        Initialize(player, enemy, damage);
    }

    @Override
    public void update()
    {
        if (action != null)
        {
            action.update();

            if (action.isDone)
            {
                if (GameUtilities.IsFatal(target, true))
                {
                    for (AbstractMonster m : GameUtilities.GetEnemies(true))
                    {
                        Explosion(m.hb);
                    }

                    final int[] damage = DamageInfo.createDamageMatrix(rose.secondaryValue, true);
                    GameActions.Bottom.DealDamageToAll(damage, DamageInfo.DamageType.THORNS, AttackEffect.NONE)
                    .SetVFX(true, false);
                }
                else if (times > 1)
                {
                    GameActions.Bottom.Add(new RoseDamageAction((AbstractMonster) target, rose, times - 1, damage));
                }

                Complete();
            }
        }
        else
        {
            Explosion(target.hb);

            action = new DealDamage(target, new DamageInfo(player, damage, rose.damageTypeForTurn), AttackEffects.GUNSHOT)
            .SetSoundPitch(0.25f, 0.45f)
            .SetPiercing(true, false)
            .SetVFX(true, false);
        }
    }

    private void Explosion(Hitbox hb)
    {
        GameActions.Top.VFX(new ExplosionSmallEffect(hb.cX + MathUtils.random(-0.05f, 0.05f),
                                                     hb.cY + MathUtils.random(-0.05f, 0.05f)),0f);
    }
}