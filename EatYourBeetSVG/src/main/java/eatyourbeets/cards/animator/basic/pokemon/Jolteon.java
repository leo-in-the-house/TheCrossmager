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

public class Jolteon extends PokemonCard {
    public static final EYBCardData DATA = Register(Jolteon.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.Random);

    public Jolteon() {
        super(DATA);

        Initialize(18, 0, 3);
        SetUpgrade(3, 0, 0);

        SetAffinity_Yellow(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.LIGHTNING)
                .AddCallback(enemy -> {
                    GameActions.Top.ApplyVulnerable(p, enemy, magicNumber);
                });
    }
}