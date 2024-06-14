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

public class Litleo extends PokemonCard {
    public static final EYBCardData DATA = Register(Litleo.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Litleo() {
        super(DATA);

        Initialize(9, 0, 0);
        SetUpgrade(3, 0, 0);
        SetEvolution(new Pyroar());

        SetAffinity_Red(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.CLAW);
    }
}