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

public class Venipede extends PokemonCard {
    public static final EYBCardData DATA = Register(Venipede.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Venipede() {
        super(DATA);

        Initialize(12, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Green(1);
        SetAffinity_Violet(1);

        SetEvolution(new Whirlipede());
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            boolean hasHindrance = false;

            for (AbstractCard card : player.hand.group) {
                if (GameUtilities.IsHindrance(card)) {
                    hasHindrance = true;
                    break;
                }
            }

            return hasHindrance;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.BITE);
    }
}