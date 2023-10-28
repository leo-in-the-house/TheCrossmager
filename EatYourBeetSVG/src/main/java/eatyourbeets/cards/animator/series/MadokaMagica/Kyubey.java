package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.CreateRandomCurses;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;

public class Kyubey extends AnimatorCard implements OnAddToDeckListener
{
    public static final EYBCardData DATA = Register(Kyubey.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Kyubey()
    {
        super(DATA);

        Initialize(0, 0, 2, 4);
        SetCostUpgrade(-1);

        SetAffinity_Star(1);

        SetExhaust(true);
    }

    @Override
    public boolean OnAddToDeck()
    {
        GameEffects.TopLevelQueue.ShowAndObtain(CreateRandomCurses.GetRandomCurse(AbstractDungeon.cardRng));

        return true;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.Draw(magicNumber);

        int energyToGain = CombatStats.Affinities.GetAffinityLevel(Affinity.Black) / secondaryValue;

        if (energyToGain > 0) {
            GameActions.Bottom.GainEnergy(energyToGain);
        }
    }
}