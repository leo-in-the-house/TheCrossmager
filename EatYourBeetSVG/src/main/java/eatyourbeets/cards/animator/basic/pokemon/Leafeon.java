package eatyourbeets.cards.animator.basic.pokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Leafeon extends PokemonCard {
    public static final EYBCardData DATA = Register(Leafeon.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Ranged, EYBCardTarget.ALL);

    public Leafeon() {
        super(DATA);

        Initialize(5, 0, 3);
        SetUpgrade(1, 0, 0);

        SetFading(true);
        SetRetain(true);

        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            for (int i=0; i<magicNumber; i++) {
                GameActions.Bottom.VFX(VFX.ThrowDagger(enemy.hb, 0.15f).SetColor(Color.GREEN));
                GameActions.Bottom.DealDamage(this, enemy, AttackEffects.POISON);
                GameActions.Bottom.Wait(0.8f);
            }
        }
    }
}