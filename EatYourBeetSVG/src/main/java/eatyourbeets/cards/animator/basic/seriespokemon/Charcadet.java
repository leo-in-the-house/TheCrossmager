package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.CardGroup;
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

public class Charcadet extends PokemonCard {
    public static final EYBCardData DATA = Register(Charcadet.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);
    static
    {
        DATA.AddPreview(new Ceruledge(), false);
        DATA.AddPreview(new Armarouge(), false);
    }

    public Charcadet() {
        super(DATA);

        Initialize(10, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Red(1);

        SetExhaust(true);
        HasSpecialEvolution();
    }

    @Override
    public void Evolve() {
        CardGroup evolutions = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        evolutions.addToTop(new Ceruledge());
        evolutions.addToTop(new Armarouge());

        PokemonCard chosenCard = (PokemonCard) evolutions.getRandomCard(rng);

        if (upgraded) {
            chosenCard.upgrade();
        }

        EvolveInto(chosenCard);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
    }
}