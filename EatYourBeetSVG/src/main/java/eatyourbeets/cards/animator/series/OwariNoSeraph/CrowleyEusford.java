package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.tokens.AffinityToken;
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
                data.AddPreview(AffinityToken.GetCard(Affinity.Red), true);
                data.AddPreview(AffinityToken.GetCard(Affinity.Green), true);
            });

    public CrowleyEusford()
    {
        super(DATA);

        Initialize(16, 0, 2);
        SetUpgrade(5, 0, 0);

        SetAffinity_Red(1, 0, 2);
        SetAffinity_Green(1, 0, 2);

        SetFading(true);
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