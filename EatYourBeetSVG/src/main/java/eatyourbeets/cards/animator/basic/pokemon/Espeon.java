package eatyourbeets.cards.animator.basic.pokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Espeon extends PokemonCard {
    public static final EYBCardData DATA = Register(Espeon.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.Normal);

    public Espeon() {
        super(DATA);

        Initialize(16, 0, 2);
        SetUpgrade(3, 0, 0);

        SetAffinity_Pink(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(VFX.ShockWave(p.hb, Color.PINK, ShockWaveEffect.ShockWaveType.NORMAL));
        GameActions.Bottom.Wait(3f);
        GameActions.Bottom.VFX(VFX.Mindblast(m.animX, m.animY));
        GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE);

        GameActions.Bottom.DrawNextTurn(magicNumber);
    }
}