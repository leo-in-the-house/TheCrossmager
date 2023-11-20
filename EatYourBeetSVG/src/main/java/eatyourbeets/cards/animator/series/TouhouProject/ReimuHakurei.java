package eatyourbeets.cards.animator.series.TouhouProject;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.tokens.AffinityToken;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class ReimuHakurei extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ReimuHakurei.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Ranged)
            
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(AffinityToken.GetCard(Affinity.White), true));

    public ReimuHakurei()
    {
        super(DATA);

        Initialize(2, 0, 3);
        SetUpgrade(0, 0, 2);

        SetAffinity_White(1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE)
            .SetDamageEffect(target ->
            {
                SFX.Play(SFX.ATTACK_MAGIC_BEAM_SHORT, 0.9f, 1.1f);
                GameEffects.List.Add(VFX.SmallLaser(p.hb, target.hb, Color.RED));
                return 0f;
            });
        }

        GameActions.Bottom.ApplyWeak(player, m, 1);
        GameActions.Bottom.ApplyVulnerable(player, m, 1);
    }
}

