package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Morgrem extends PokemonCard {
    public static final EYBCardData DATA = Register(Morgrem.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.Random);

    public Morgrem() {
        super(DATA);

        Initialize(7, 0, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_White(1, 0, 3);
        SetAffinity_Black(1, 0, 3);

        SetEvolution(new Grimmsnarl());
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
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

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.DARK);
        }
    }
}