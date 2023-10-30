package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import eatyourbeets.actions.orbs.ShuffleOrbs;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Jibril extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Jibril.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Jibril()
    {
        super(DATA);

        Initialize(9, 0);
        SetUpgrade(8, 0);

        SetAffinity_Black(2);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(VFX.ShockWave(p.hb, Color.VIOLET), 0.3f);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.FIRE);
        GameActions.Bottom.ChannelOrb(new Dark());
        GameActions.Bottom.Add(new ShuffleOrbs(1));
    }
}
