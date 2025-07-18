package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.colorless.uncommon.Kuroyukihime;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;

public class Kuroyukihime_BlackLotus extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Kuroyukihime_BlackLotus.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Ranged, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(Kuroyukihime.DATA.Series);

    public Kuroyukihime_BlackLotus()
    {
        super(DATA);

        Initialize(7, 4);
        SetUpgrade(3, 3);

        SetAffinity_Green(1);

        SetAffinityRequirement(Affinity.Green, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.SFX(SFX.ATTACK_DEFECT_BEAM);
        GameActions.Bottom.VFX(VFX.SweepingBeam(p.hb, VFX.FlipHorizontally(), new Color(0.24f, 0, 0.4f, 1f)), 0.3f);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.FIRE);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.Draw(2);
            GameActions.Bottom.GainAffinity(Affinity.Green);
            GameActions.Bottom.ModifyAllInstances(uuid)
            .AddCallback(card ->
            {
                final Kuroyukihime_BlackLotus c = (Kuroyukihime_BlackLotus)card;
                c.SetAffinityRequirement(Affinity.Green, c.affinities.GetRequirement(Affinity.Green) + 1);
            });
        }
    }
}