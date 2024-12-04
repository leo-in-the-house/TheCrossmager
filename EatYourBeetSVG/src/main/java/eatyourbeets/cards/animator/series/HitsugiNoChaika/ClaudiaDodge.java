package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ClaudiaDodge extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ClaudiaDodge.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public ClaudiaDodge()
    {
        super(DATA);

        Initialize(0, 0);
        SetUpgrade(0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(1);
        SetAffinity_Green(1);

        SetDelayed(true);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ChannelRandomOrb(1)
            .AddCallback(orbs -> {
                if (orbs.size() > 0) {
                    GameActions.Top.Reload(name, cards ->
                    {
                        if (cards.size() > 0)
                        {
                            GameActions.Bottom.TriggerOrbPassive(orbs.get(0), cards.size());
                            GameActions.Bottom.TriggerOrbPassive(orbs.get(0), cards.size());
                        }
                    });
                }
            });

    }
}