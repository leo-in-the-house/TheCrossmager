package eatyourbeets.cards.animator.series.Overlord;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class CZDelta extends AnimatorCard
{
    public static final EYBCardData DATA = Register(CZDelta.class)
            .SetAttack(0, CardRarity.COMMON, EYBAttackType.Ranged, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();

    private static final Color VFX_COLOR = new Color(0.6f, 1f, 0.6f, 1f);

    public CZDelta()
    {
        super(DATA);

        Initialize(4, 0, 0);
        SetUpgrade(2, 0, 0);

        SetAffinity_Teal(1, 0, 0);
        SetAffinity_Brown(1, 0, 0);
    }

    @Override
    protected void OnUpgrade()
    {
        SetAttackTarget(EYBCardTarget.Normal);
        this.upgradedDamage = true;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        if (upgraded)
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT)
            .SetDamageEffect(c ->
            {
                SFX.Play(SFX.ATTACK_MAGIC_BEAM_SHORT, 0.9f, 1.1f, 0.95f);
                return GameEffects.List.Add(VFX.SmallLaser(player.hb, c.hb, VFX_COLOR, 0.1f)).duration * 0.7f;
            })
            .SetSoundPitch(1.5f, 1.55f)
            .SetVFXColor(VFX_COLOR);
        }
        else
        {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.GUNSHOT)
            .SetDamageEffect(c ->
            {
                SFX.Play(SFX.ATTACK_MAGIC_BEAM_SHORT, 0.9f, 1.1f, 0.95f);
                return GameEffects.List.Add(VFX.SmallLaser(player.hb, c.hb, VFX_COLOR, 0.1f)).duration * 0.7f;
            })
            .SetSoundPitch(1.5f, 1.55f)
            .SetVFXColor(VFX_COLOR);
        }

        GameActions.Bottom.ApplyVulnerable(TargetHelper.AllCharacters(), 1);
    }
}