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

public class Armarouge extends PokemonCard {
    public static final EYBCardData DATA = Register(Armarouge.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.Normal);

    public Armarouge() {
        super(DATA);

        Initialize(8, 0, 3);
        SetUpgrade(1, 0, 0);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Pink(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        SetUnplayable(player.hand.group.size() <= 0);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ExhaustFromHand(name, 1, false)
                .SetOptions(false, false, false);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
        }
    }
}