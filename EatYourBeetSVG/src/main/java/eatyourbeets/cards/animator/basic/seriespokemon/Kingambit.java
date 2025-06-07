package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Kingambit extends PokemonCard {
    public static final EYBCardData DATA = Register(Kingambit.class)
            .SetAttack(3, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.ALL);

    public Kingambit() {
        super(DATA);

        Initialize(11, 0, 2);
        SetUpgrade(3, 0, 0);

        SetAffinity_Violet(1);
        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SMASH);

        GameActions.Bottom.ApplyVulnerable(TargetHelper.Enemies(), magicNumber);
        GameActions.Bottom.ApplyWeak(TargetHelper.Enemies(), magicNumber);
        GameActions.Bottom.ApplyLockOn(TargetHelper.Enemies(), magicNumber);
    }
}