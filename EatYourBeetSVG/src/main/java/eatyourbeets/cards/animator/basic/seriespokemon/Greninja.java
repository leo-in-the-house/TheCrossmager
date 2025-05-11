package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Greninja extends PokemonCard {
    public static final EYBCardData DATA = Register(Greninja.class)
            .SetAttack(3, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Greninja() {
        super(DATA);

        Initialize(7, 0, 3);
        SetUpgrade(1, 0, 0);

        SetAffinity_Blue(1);
        SetAffinity_Violet(1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.DAGGER);
        }

        GameActions.Bottom.ChannelOrb(new Water());
        GameActions.Bottom.ChannelOrb(new Dark());
    }
}