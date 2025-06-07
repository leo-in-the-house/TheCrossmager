package eatyourbeets.cards.animator.basic.seriespokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Flygon extends PokemonCard {
    public static final EYBCardData DATA = Register(Flygon.class)
            .SetAttack(3, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Flygon() {
        super(DATA);

        Initialize(10, 0, 3);
        SetUpgrade(1, 0, 0);

        SetAffinity_Brown(1);
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
            GameActions.Bottom.VFX(VFX.SweepingBeam(p.hb, VFX.FlipHorizontally(), new Color(1f, 1f, 1f, 1f)), 0.1f);
            GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
        }

        CostModifiers.For(this).Add(1);
        GameUtilities.IncreaseMagicNumber(this, this.magicNumber * 2, false);
    }
}