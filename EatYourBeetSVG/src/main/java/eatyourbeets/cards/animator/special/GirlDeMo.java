package eatyourbeets.cards.animator.special;

import basemod.Pair;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.interfaces.delegates.ActionT1;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

import java.util.ArrayList;
import java.util.Comparator;

public class GirlDeMo extends AnimatorCard
{
    public static final EYBCardData DATA = Register(GirlDeMo.class)
            .SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.AngelBeats);

    public GirlDeMo()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Yellow(2);
        SetAffinity_White(1);
        SetAffinity_Black(1);

        SetEthereal(true);
        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetExhaust(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        ArrayList<Pair<ActionT1<Integer>, Integer>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(GameActions.Bottom::GainRed, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Red)));
        pairs.add(new Pair<>(GameActions.Bottom::GainGreen, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Green)));
        pairs.add(new Pair<>(GameActions.Bottom::GainBlue, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Blue)));
        pairs.add(new Pair<>(GameActions.Bottom::GainBlack, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Black)));
        pairs.add(new Pair<>(GameActions.Bottom::GainWhite, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.White)));
        pairs.add(new Pair<>(GameActions.Bottom::GainTeal, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Teal)));
        pairs.add(new Pair<>(GameActions.Bottom::GainYellow, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Yellow)));
        pairs.add(new Pair<>(GameActions.Bottom::GainPink, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Pink)));
        pairs.add(new Pair<>(GameActions.Bottom::GainBrown, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Brown)));
        pairs.add(new Pair<>(GameActions.Bottom::GainViolet, 2 * CombatStats.Affinities.GetAffinityLevel(Affinity.Violet)));
        pairs.sort(Comparator.comparingInt(Pair::getValue));

        int amount = pairs.get(9).getValue();
        if (amount > 0)
        {
            pairs.get(9).getKey().Invoke(amount);

            for (int i = 8; i >= 0; i--) {
                if (pairs.get(i).getValue().equals(amount))
                {
                    pairs.get(i).getKey().Invoke(amount);
                }
                else {
                    break;
                }
            }
        }
    }
}