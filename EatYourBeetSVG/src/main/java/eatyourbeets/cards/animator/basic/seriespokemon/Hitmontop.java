package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Hitmontop extends PokemonCard {
    public static final EYBCardData DATA = Register(Hitmontop.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Hitmontop() {
        super(DATA);

        Initialize(3, 3, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Red(1, 0, 3);
        this.scalesBothAttackAndBlock = true;
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);
        }

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }
    }
}