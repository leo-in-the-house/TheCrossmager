package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class SaoriJoumae_Misaki extends AnimatorCard {
    public static final EYBCardData DATA = Register(SaoriJoumae_Misaki.class)
            .SetAttack(3, CardRarity.SPECIAL, EYBAttackType.Ranged, EYBCardTarget.ALL)
            .SetSeries(CardSeries.BlueArchive);

    public SaoriJoumae_Misaki() {
        super(DATA);

        Initialize(80, 0, 3);
        SetUpgrade(12, 0, 0);
        SetAffinity_Green(1, 0, 2);
        SetAffinity_Red(1, 0, 2);
        SetAffinity_Black(1, 0, 2);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Callback(() ->
        {
            for (AbstractCreature m1 : GameUtilities.GetEnemies(true))
            {
                GameEffects.List.Attack(player, m1, AttackEffects.LIGHTNING, 0.7f, 0.8f, Color.RED);
                GameEffects.List.Add(VFX.FlameBarrier(m1.hb));
                for (int i = 0; i < 12; i++)
                {
                    GameEffects.List.Add(VFX.SmallExplosion(m1.hb, 0.4f).PlaySFX(i == 0));
                }
            }
        });
        GameActions.Bottom.WaitRealtime(0.35f);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE).SetVFX(true, true);

        GameActions.Bottom.LoseHP(null, player, magicNumber, AttackEffects.DAGGER)
                .IgnoreTempHP(false)
                .CanKill(true);
    }
}