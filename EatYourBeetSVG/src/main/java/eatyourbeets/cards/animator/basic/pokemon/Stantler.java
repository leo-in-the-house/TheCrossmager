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

public class Stantler extends PokemonCard {
    public static final EYBCardData DATA = Register(Stantler.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Random);

    public Stantler() {
        super(DATA);

        Initialize(2, 3, 3);
        SetUpgrade(1, 0, 0);
        SetEvolution(new Wyrdeer());

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
    }
}