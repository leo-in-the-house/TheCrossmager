package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import eatyourbeets.cards.animator.special.MatouShinji_CommandSpell;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MatouShinji extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MatouShinji.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.Random)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new MatouShinji_CommandSpell(), false));

    public MatouShinji()
    {
        super(DATA);

        Initialize(0, 2, 8);
        SetUpgrade(0, 3, 3);

        SetAffinity_Violet(2, 0, 1);

        SetAffinityRequirement(Affinity.Black, 5);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block)
        .SetVFX(true, true);
        GameActions.Bottom.Callback(() ->
        {
            final AbstractMonster enemy = GameUtilities.GetRandomEnemy(true);
            if (GameUtilities.IsValidTarget(enemy))
            {
                GameActions.Top.ApplyPoison(player, enemy, magicNumber);
                GameActions.Top.VFX(new PotionBounceEffect(player.hb.cX, player.hb.cY, enemy.hb.cX, enemy.hb.cY), 0.3f);
            }
        })
        .SetDuration(0.02f, false);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.MakeCardInHand(new MatouShinji_CommandSpell())
                    .SetUpgrade(upgraded, true);
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return super.CheckSpecialConditionLimited(tryUse, super::CheckSpecialCondition);
    }
}
