package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MatouSakura extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MatouSakura.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage();

    public MatouSakura()
    {
        super(DATA);

        Initialize(0, 6);
        SetUpgrade(0, 3);

        SetAffinity_Black(1, 0, 2);
        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ChannelOrb(new Dark())
        .AddCallback(orbs -> {
            int amount = CombatStats.Affinities.GetAffinityLevel(Affinity.Black);

            if (!upgraded) {
                amount /= 2;
            }

            if (amount > 0) {
                for (AbstractOrb o : orbs)
                {
                    GameActions.Bottom.TriggerOrbPassive(o, amount);
                }
            }
        });
    }
}