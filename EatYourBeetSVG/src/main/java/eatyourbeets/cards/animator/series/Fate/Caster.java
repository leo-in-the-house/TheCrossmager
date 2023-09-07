package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;

public class Caster extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Caster.class)
            .SetSkill(0, CardRarity.COMMON)
            
            .SetSeriesFromClassPackage();

    public Caster()
    {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, -2);

        SetAffinity_Blue(1);

        SetEthereal(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        int amount = CombatStats.Affinities.GetAffinityLevel(Affinity.Black) / 4;

        if (amount > 0)
        {
            GameActions.Bottom.ChannelOrbs(Dark::new, amount);
        }
    }
}