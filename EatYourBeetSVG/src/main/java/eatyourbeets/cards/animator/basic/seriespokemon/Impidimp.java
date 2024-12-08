package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Impidimp extends PokemonCard {
    public static final EYBCardData DATA = Register(Impidimp.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Random);

    public Impidimp() {
        super(DATA);

        Initialize(7, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_White(1, 0, 3);
        SetAffinity_Black(1, 0, 3);

        SetEvolution(new Morgrem());
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        AddScaling(Affinity.White, 1);
        AddScaling(Affinity.Black, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.CLAW);
    }
}