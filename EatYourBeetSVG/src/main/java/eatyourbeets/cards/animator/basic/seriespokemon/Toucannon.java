package eatyourbeets.cards.animator.basic.seriespokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Toucannon extends PokemonCard {
    public static final EYBCardData DATA = Register(Toucannon.class)
            .SetAttack(3, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Toucannon() {
        super(DATA);

        Initialize(2, 0, 14, 3);
        SetUpgrade(0, 0, 0);

        SetAffinity_Blue(1);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetHaste(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(new BorderFlashEffect(Color.RED));
        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);
        }

        GameActions.Bottom.GainFlameBarrier(secondaryValue);
    }
}