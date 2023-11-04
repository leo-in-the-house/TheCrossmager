package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Shalltear extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shalltear.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeries(CardSeries.Overlord);

    public Shalltear()
    {
        super(DATA);

        Initialize(6, 0, 2);
        SetUpgrade(3, 0, 1);

        SetAffinity_Violet(1, 0, 1);
        SetAffinity_Black(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE)
        .SetDamageEffect((enemy, __) -> GameEffects.List.Add(VFX.Hemokinesis(player.hb, enemy.hb)));

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true))
        {
            if (GameUtilities.GetCommonDebuffs(TargetHelper.Normal(enemy)).size() > 0) {
                GameActions.Bottom.ReduceStrength(enemy, magicNumber,true);
                GameActions.Bottom.GainPestilence(1);
            }
        }
    }
}