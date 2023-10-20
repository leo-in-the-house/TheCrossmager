package eatyourbeets.cards.animator.series.Konosuba;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Vanir extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Vanir.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Elemental)
            .SetSeriesFromClassPackage();

    public Vanir()
    {
        super(DATA);

        Initialize(15, 0, 3);
        SetUpgrade(3, 0, -1);

        SetAffinity_Black(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        if (damage > 8)
        {
            GameActions.Bottom.SFX(SFX.ATTACK_DEFECT_BEAM);
            GameActions.Bottom.VFX(VFX.SmallLaser(p.hb, m.hb, Color.RED));
            GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE)
            .SetDamageEffect(c -> GameEffects.List.Add(VFX.SmallExplosion(c.hb)).duration * 0.1f);
        }
        else
        {
            GameActions.Bottom.Wait(0.25f);
            GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
        }

        GameActions.Bottom.ModifyAllInstances(uuid, c -> c.baseDamage = Math.max(0, c.baseDamage - c.magicNumber));
    }
}