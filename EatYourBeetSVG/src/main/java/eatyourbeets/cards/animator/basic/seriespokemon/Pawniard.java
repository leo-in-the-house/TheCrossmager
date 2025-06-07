package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Pawniard extends PokemonCard {
    public static final EYBCardData DATA = Register(Pawniard.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Pawniard() {
        super(DATA);

        Initialize(3, 0, 2);
        SetUpgrade(3, 0, 0);

        SetAffinity_Violet(1);
        SetAffinity_Teal(1);

        SetEvolution(new Bisharp());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.SMASH);

        GameActions.Bottom.ApplyVulnerable(p, m, magicNumber);
    }
}