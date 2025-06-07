package eatyourbeets.cards.animator.basic.seriespokemon;

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

public class Ceruledge extends PokemonCard {
    public static final EYBCardData DATA = Register(Ceruledge.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.ALL);

    public Ceruledge() {
        super(DATA);

        Initialize(22, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Black(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        SetUnplayable(player.hand.group.size() <= 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ExhaustFromHand(name, 1, false)
                .SetOptions(false, false, false);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_DIAGONAL);
    }
}