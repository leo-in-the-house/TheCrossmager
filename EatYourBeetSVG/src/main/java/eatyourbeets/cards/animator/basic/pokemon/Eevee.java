package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Eevee extends PokemonCard {
    public static final EYBCardData DATA = Register(Eevee.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);
    static
    {
        DATA.AddPreview(new Jolteon(), false);
        DATA.AddPreview(new Flareon(), false);
        DATA.AddPreview(new Vaporeon(), false);
        DATA.AddPreview(new Espeon(), false);
        DATA.AddPreview(new Umbreon(), false);
        DATA.AddPreview(new Leafeon(), false);
        DATA.AddPreview(new Glaceon(), false);
        DATA.AddPreview(new Sylveon(), false);
    }

    public Eevee() {
        super(DATA);

        Initialize(5, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Star(1);


    }

    @Override
    public void Evolve() {
        CardGroup eeveelutions = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        eeveelutions.addToTop(new Jolteon());
        eeveelutions.addToTop(new Flareon());
        eeveelutions.addToTop(new Vaporeon());
        eeveelutions.addToTop(new Espeon());
        eeveelutions.addToTop(new Umbreon());
        eeveelutions.addToTop(new Leafeon());
        eeveelutions.addToTop(new Glaceon());
        eeveelutions.addToTop(new Sylveon());

        PokemonCard chosenCard = (PokemonCard) eeveelutions.getRandomCard(rng);
        chosenCard.upgrade();

        EvolveInto(chosenCard);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.CLAW);
    }
}