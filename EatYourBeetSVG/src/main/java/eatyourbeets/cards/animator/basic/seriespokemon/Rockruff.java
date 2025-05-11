package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.*;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Rockruff extends PokemonCard {
    public static final EYBCardData DATA = Register(Rockruff.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);
    static
    {
        DATA.AddPreview(new Lycanroc_Midday(), false);
        DATA.AddPreview(new Lycanroc_Midnight(), false);
        DATA.AddPreview(new Lycanroc_Dusk(), false);
    }


    public Rockruff() {
        super(DATA);

        Initialize(13, 0, 2);
        SetUpgrade(3, 0, 0);

        SetAffinity_Brown(1);

        HasSpecialEvolution();
    }

    @Override
    public void Evolve() {
        CardGroup lycanrocForms = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        lycanrocForms.addToTop(new Lycanroc_Midday());
        lycanrocForms.addToTop(new Lycanroc_Midnight());
        lycanrocForms.addToTop(new Lycanroc_Dusk());

        PokemonCard chosenCard = (PokemonCard) lycanrocForms.getRandomCard(rng);

        if (upgraded) {
            chosenCard.upgrade();
        }

        EvolveInto(chosenCard);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            int numAttacksInHand = 0;

            for (AbstractCard card : player.hand.group) {
                if (card.type == CardType.ATTACK && !card.uuid.equals(this.uuid)) {
                   numAttacksInHand++;
                }
            }

            return numAttacksInHand >= magicNumber;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.CLAW);
    }
}