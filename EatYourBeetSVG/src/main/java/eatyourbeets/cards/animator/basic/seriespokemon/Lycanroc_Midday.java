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

public class Lycanroc_Midday extends PokemonCard {
    public static final EYBCardData DATA = Register(Lycanroc_Midday.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Lycanroc_Midday() {
        super(DATA);

        Initialize(22, 0, 2);
        SetUpgrade(3, 0, 0);

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
        GameUtilities.PlayVoiceSFX("Lycanroc (Midday)");

        GameActions.Bottom.DealDamage(this, m, AttackEffects.CLAW);
    }
}