package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Pancham extends PokemonCard {
    public static final EYBCardData DATA = Register(Pancham.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Pancham() {
        super(DATA);

        Initialize(5, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Red(1);
        SetAffinity_Violet(1);

        SetEvolution(new Pangoro());
    }

    @Override
    protected float ModifyDamage(AbstractMonster enemy, float amount)
    {
        if (GameUtilities.InStance(WrathStance.STANCE_ID))
        {
            amount *= 2;
        }

        return super.ModifyDamage(enemy, amount);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
    }
}