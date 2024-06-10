package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.VFX;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Megunee_Zombie extends AnimatorCard implements OnStartOfTurnPostDrawSubscriber {
    public static final EYBCardData DATA = Register(Megunee_Zombie.class)
            .SetAttack(-1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeries(CardSeries.GakkouGurashi);

    public Megunee_Zombie() {
        super(DATA);

        Initialize(12, 0, 5);
        SetUpgrade(2, 0, 2);

        SetSeries(CardSeries.GakkouGurashi);
        SetAffinity_Black(1, 0, 2);
        SetAffinity_Violet(1, 0, 2);
        SetAffinity_White(1, 0, 2);

        SetRicochet(2, 0, this::OnCooldownCompleted);

        SetAutoplayed(true);
        SetExhaust(true);
        SetMultiDamage(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        this.freeToPlayOnce = false;
        int stacks = GameUtilities.UseXCostEnergy(this);

        for (int i=0; i<stacks; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AbstractGameAction.AttackEffect.NONE)
                    .SetDamageEffect(e -> {
                        GameEffects.List.Add(VFX.Bite(e.hb, Color.RED));
                        return 0.02f;
                    })
                    .AddCallback(info, (info2, enemy) ->
                    {
                        if (GameUtilities.IsFatal(enemy, true)) {
                            GameActions.Top.Heal(magicNumber);
                        }
                    });
            GameActions.Bottom.Add(new ShakeScreenAction(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.LOW));
        }
    }

    @Override
    public void OnStartOfTurnPostDraw()
    {
        if (player.exhaustPile.contains(this))
        {
            GameEffects.List.ShowCopy(this, Settings.WIDTH * 0.75f, Settings.HEIGHT * 0.4f);

            cooldown.ProgressCooldownAndTrigger(null);
        }
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onStartOfTurnPostDraw.Subscribe(this);
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.MoveCard(this, player.exhaustPile, player.drawPile)
                .ShowEffect(true, false);
    }
}