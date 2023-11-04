package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;
import eatyourbeets.utilities.TargetHelper;

public class Sebas extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Sebas.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Sebas()
    {
        super(DATA);

        Initialize(0, 11, 40);
        SetCostUpgrade(-1);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Brown(1, 0, 1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block).AddCallback(() ->
        {
            if (player.currentBlock >= magicNumber) {
                GameActions.Top.ChangeStance(WrathStance.STANCE_ID);
            }

            for (AbstractMonster enemy : JUtils.Filter(GameUtilities.GetEnemies(true), e -> (GameUtilities.GetCommonDebuffs(TargetHelper.Normal(e)).size() > 0)))
            {
                GameActions.Top.DealDamage(player, enemy, player.currentBlock, DamageInfo.DamageType.THORNS, AttackEffects.BLUNT_HEAVY);
            }
        });
    }
}