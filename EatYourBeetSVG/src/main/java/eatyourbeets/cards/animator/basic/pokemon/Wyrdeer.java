package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Wyrdeer extends PokemonCard {
    public static final EYBCardData DATA = Register(Wyrdeer.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Random);

    public Wyrdeer() {
        super(DATA);

        Initialize(4, 7, 3);
        SetUpgrade(1, 0, 0);

        SetAffinity_Pink(1);
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
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.BLUNT_HEAVY);
        }

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.Draw(1);
    }
}