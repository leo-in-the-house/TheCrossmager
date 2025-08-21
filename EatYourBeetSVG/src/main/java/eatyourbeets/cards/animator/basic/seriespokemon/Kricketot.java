package eatyourbeets.cards.animator.basic.seriespokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kricketot extends PokemonCard {
    public static final EYBCardData DATA = Register(Kricketot.class)
            .SetAttack(0, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Random);

    public Kricketot() {
        super(DATA);

        Initialize(2, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Green(1);
        SetEvolution(new Kricketune());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(VFX.ShockWave(p.hb, Color.GREEN, ShockWaveEffect.ShockWaveType.NORMAL));
        GameActions.Bottom.Wait(0.5f);
        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.BLUNT_LIGHT);
    }
}