package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SSS_Rixia extends AnimatorCard {
    public static final EYBCardData DATA = Register(SSS_Rixia.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public SSS_Rixia() {
        super(DATA);

        Initialize(15, 0, 0);
        SetUpgrade(4, 0, 0);

        SetAffinity_Black(1, 0, 1);

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            if (!GameUtilities.IsAttacking(enemy.intent)) {
                GameActions.Bottom.DealDamage(this, enemy, AttackEffects.SLASH_HEAVY)
                        .SetVFXColor(Color.PURPLE);
            }
        }


    }
}