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

public class Heliolisk extends PokemonCard {
    public static final EYBCardData DATA = Register(Heliolisk.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.ALL);

    public Heliolisk() {
        super(DATA);

        Initialize(9, 0, 4);
        SetUpgrade(3, 0, 0);

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