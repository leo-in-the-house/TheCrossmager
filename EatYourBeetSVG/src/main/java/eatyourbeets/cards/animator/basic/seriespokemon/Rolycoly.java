package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Rolycoly extends PokemonCard {
    public static final EYBCardData DATA = Register(Rolycoly.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Rolycoly() {
        super(DATA);

        Initialize(5, 0, 3);
        SetUpgrade(3, 0, 0);
        SetEvolution(new Carkol());

        SetAffinity_Red(1);
        SetAffinity_Brown(1);
    }

    @Override
    protected float GetInitialDamage()
    {
        float damage = super.GetInitialDamage();
        damage += (JUtils.Count(player.orbs, o -> Lightning.ORB_ID.equals(o.ID)) * magicNumber);

        return damage;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
    }
}