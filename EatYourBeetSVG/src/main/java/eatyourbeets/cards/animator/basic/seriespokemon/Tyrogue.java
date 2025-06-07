package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Tyrogue extends PokemonCard {
    public static final EYBCardData DATA = Register(Tyrogue.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);
    static
    {
        DATA.AddPreview(new Hitmonlee(), false);
        DATA.AddPreview(new Hitmonchan(), false);
        DATA.AddPreview(new Hitmontop(), false);
    }

    public Tyrogue() {
        super(DATA);

        Initialize(3, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Red(1, 0, 2);
        HasSpecialEvolution();
    }

    @Override
    public void Evolve() {
        int numAttacks = (int)player.masterDeck.group.stream().filter(card -> card.type == CardType.ATTACK).count();
        int numSkills = (int)player.masterDeck.group.stream().filter(card -> card.type == CardType.SKILL).count();

        if (numAttacks > numSkills) {
            EvolveInto(new Hitmonlee());
        }
        else if (numAttacks < numSkills) {
            EvolveInto(new Hitmonchan());
        }
        else {
            EvolveInto(new Hitmontop());
        }
    }


    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.PUNCH);
        }
    }
}