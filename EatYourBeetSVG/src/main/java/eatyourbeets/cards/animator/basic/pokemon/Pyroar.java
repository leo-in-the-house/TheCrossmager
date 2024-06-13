package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Pyroar extends PokemonCard {
    public static final EYBCardData DATA = Register(Pyroar.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Pyroar() {
        super(DATA);

        Initialize(22, 0, 0);
        SetUpgrade(3, 0, 0);
        SetEvolved(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
    }
}