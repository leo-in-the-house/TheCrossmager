package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
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

public class Lycanroc_Midnight extends PokemonCard {
    public static final EYBCardData DATA = Register(Lycanroc_Midnight.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Lycanroc_Midnight() {
        super(DATA);

        Initialize(10, 10, 2);
        SetUpgrade(1, 2, 0);

        SetAffinity_Brown(1);
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
        GameUtilities.PlayVoiceSFX("Lycanroc (Midnight)");

        GameActions.Bottom.DealDamage(this, m, AttackEffects.CLAW);
        GameActions.Bottom.GainBlock(block);
    }
}