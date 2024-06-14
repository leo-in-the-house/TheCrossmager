package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Helioptile extends PokemonCard {
    public static final EYBCardData DATA = Register(Helioptile.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.ALL);

    public Helioptile() {
        super(DATA);

        Initialize(4, 0, 1);
        SetUpgrade(3, 0, 0);
        SetEvolution(new Heliolisk());

        SetAffinity_Yellow(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.LIGHTNING)
                .AddCallback(enemies -> {
                    for (AbstractCreature enemy : enemies) {
                        GameActions.Top.GainTemporaryHP(magicNumber);
                    }
                });
    }
}