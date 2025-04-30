package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Grimmsnarl extends PokemonCard {
    public static final EYBCardData DATA = Register(Grimmsnarl.class)
            .SetAttack(3, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.ALL);

    public Grimmsnarl() {
        super(DATA);

        Initialize(4, 0, 3);
        SetUpgrade(0, 0, 0);

        SetAffinity_White(1, 0, 1);
        SetAffinity_Black(1, 0, 1);
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
            GameActions.Bottom.DealDamageToAll( this, AttackEffects.DARK);
        }
    }
}