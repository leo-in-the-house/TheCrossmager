package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.HiiragiMahiru_Demon;
import eatyourbeets.cards.animator.special.HiiragiMahiru_Deva;
import eatyourbeets.cards.animator.special.HiiragiMahiru_Echo;
import eatyourbeets.cards.animator.special.HiiragiMahiru_Wraith;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class CrowleyEusford extends AnimatorCard
{
    public static final EYBCardData DATA = Register(CrowleyEusford.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new HiiragiMahiru_Demon(), false);
                data.AddPreview(new HiiragiMahiru_Deva(), false);
                data.AddPreview(new HiiragiMahiru_Echo(), false);
                data.AddPreview(new HiiragiMahiru_Wraith(), false);
            });

    public CrowleyEusford()
    {
        super(DATA);

        Initialize(16, 0, 2);
        SetUpgrade(5, 0, 0);

        SetAffinity_Red(1, 0, 2);
        SetAffinity_Green(1, 0, 2);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.SLASH_HEAVY);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.ObtainAffinityToken(Affinity.Red, upgraded);
            GameActions.Bottom.ObtainAffinityToken(Affinity.Green, upgraded);
            GameActions.Bottom.Motivate(2);
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return CombatStats.CardsExhaustedThisTurn().size() > 0;
    }
}