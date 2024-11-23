package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Glaceon extends PokemonCard {
    public static final EYBCardData DATA = Register(Glaceon.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.Normal);

    public Glaceon() {
        super(DATA);

        Initialize(15, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Blue(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.ICE);

        GameActions.Bottom.ChannelOrb(new Frost());
    }
}