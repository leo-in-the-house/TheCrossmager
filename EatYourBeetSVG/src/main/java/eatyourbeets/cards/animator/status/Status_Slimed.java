package eatyourbeets.cards.animator.status;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Status_Slimed extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Status_Slimed.class)
            .SetStatus(1, CardRarity.COMMON, EYBCardTarget.None);
    private static final RandomizedList<Affinity> affinities = new RandomizedList<>();

    public Status_Slimed()
    {
        this(false);
    }

    public Status_Slimed(boolean upgrade)
    {
        super(DATA);

        Initialize(0, 0);
        SetCostUpgrade(-1);

        SetEndOfTurnPlay(false);
        SetExhaust(true);

        if (upgrade)
        {
            upgrade();
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (affinities.Size() == 0)
        {
            affinities.Add(Affinity.Red);
            affinities.Add(Affinity.Green);
            affinities.Add(Affinity.Blue);
            affinities.Add(Affinity.White);
            affinities.Add(Affinity.Black);
            affinities.Add(Affinity.Pink);
            affinities.Add(Affinity.Violet);
            affinities.Add(Affinity.Brown);
            affinities.Add(Affinity.Yellow);
            affinities.Add(Affinity.Teal);
        }

        InitializeAffinity(affinities.Retrieve(rng, false), 1, 0, 0);
    }
}