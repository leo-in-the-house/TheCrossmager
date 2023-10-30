package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
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

public class DolaSchwi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(DolaSchwi.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage();

    public DolaSchwi()
    {
        super(DATA);

        Initialize(7, 0);
        SetCostUpgrade(-1);

        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE)
        .SetDamageEffect(c ->
        {
            SFX.Play(SFX.ATTACK_FIRE, 1.25f, 1.3f);
            return GameEffects.List.Add(VFX.SmallLaser(player.hb, c.hb, Color.WHITE, 0.1f)).duration * 0.5f;
        })
        .SetSoundPitch(1.7f, 1.75f)
        .SetVFXColor(Color.WHITE);

        GameActions.Bottom.ChannelOrb(new Lightning());
    }
}