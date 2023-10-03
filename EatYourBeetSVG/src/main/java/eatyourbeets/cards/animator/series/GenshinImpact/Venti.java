package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.orbs.animator.Aether;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Venti extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Venti.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Venti()
    {
        super(DATA);

        Initialize(0, 3, 0, 0);
        SetUpgrade(0, 8, 0, 0);

        SetAffinity_Blue(1, 0, 2);
        SetAffinity_Green(1, 0, 2);

        SetEthereal(true);

        SetAffinityRequirement(Affinity.Blue, 3);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        if (CheckSpecialCondition(false)) {
            return super.GetBlockInfo().AddMultiplier(1 + CombatStats.OrbTypesChanneledThisCombat());
        }

        return super.GetBlockInfo().AddMultiplier(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChannelOrb(new Aether());
        GameActions.Bottom.ChannelRandomOrb(1);

        if (CheckSpecialCondition(false)) {
            for (int i=0; i<CombatStats.OrbTypesChanneledThisCombat(); i++) {
                GameActions.Bottom.GainBlock(block);
            }
        }
    }

}