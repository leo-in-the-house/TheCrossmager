package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Megunee_Zombie extends AnimatorCard {
    public static final EYBCardData DATA = Register(Megunee_Zombie.class)
            .SetAttack(-1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();

    public Megunee_Zombie() {
        super(DATA);

        Initialize(10, 0, 4);
        SetUpgrade(3, 0, 2);

        SetSeries(CardSeries.GakkouGurashi);
        SetAffinity_Black(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);
        SetAffinity_White(1, 0, 1);

        SetRicochet(2, 0, this::OnCooldownCompleted);

        SetAutoplayed(true);
        SetExhaust(true);
        SetMultiDamage(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);


        int stacks = GameUtilities.UseXCostEnergy(this);

        for (int i=0; i<stacks; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AbstractGameAction.AttackEffect.NONE)
                    .SetDamageEffect(e -> GameEffects.List.Add(VFX.Bite(e.hb, Color.RED)).duration)
                    .AddCallback(info, (info2, enemy) ->
                    {
                        if (GameUtilities.IsFatal(enemy, true)) {
                            GameActions.Bottom.Heal(magicNumber);
                        }
                    });
            GameActions.Bottom.Add(new ShakeScreenAction(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.LOW));
        }
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.MoveCard(this, player.exhaustPile, player.drawPile)
                .ShowEffect(true, false);
    }
}