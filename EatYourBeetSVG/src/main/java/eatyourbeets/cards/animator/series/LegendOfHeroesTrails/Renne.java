package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.*;

public class Renne extends AnimatorCard {
    public static final EYBCardData DATA = Register(Renne.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Renne() {
        super(DATA);

        Initialize(6, 0, 4);
        SetUpgrade(6, 0, -1);

        SetAffinity_Black(2, 0, 3);
        SetAffinity_Teal(2, 0, 3);

        SetDelayed(true);
        SetEthereal(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_DIAGONAL)
                .SetDamageEffect((enemy, __) -> GameEffects.List.Add(VFX.Hemokinesis(player.hb, enemy.hb)));

        int lightningCount = GameUtilities.GetOrbCount(Lightning.ORB_ID);

        if (!GameUtilities.InEliteOrBossRoom() && lightningCount >= magicNumber) {
            GameActions.Bottom.BorderLongFlash(Color.SCARLET);

            RandomizedList<AbstractMonster> validEnemies = new RandomizedList<>();
            validEnemies.AddAll(GameUtilities.GetEnemies(true));

            int numKills = lightningCount / magicNumber;

            for (int i=0; i<numKills; i++) {
                if (validEnemies.Size() == 0) {
                    break;
                }

                AbstractMonster victim = validEnemies.Retrieve(rng, true);
                GameActions.Bottom.Add(new InstantKillAction(victim));
            }
        }
    }
}