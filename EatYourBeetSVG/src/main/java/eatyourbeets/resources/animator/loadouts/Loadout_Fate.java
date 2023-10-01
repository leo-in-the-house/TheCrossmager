package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.Fate.*;
import eatyourbeets.cards.animator.ultrarare.JeanneDArc;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Fate extends AnimatorLoadout
{
    public Loadout_Fate()
    {
        super(CardSeries.Fate);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Berserker.DATA, 6);
        AddStarterCard(Assassin.DATA, 4);
        AddStarterCard(EmiyaShirou.DATA, 5);
        AddStarterCard(Alexander.DATA, 7);
        AddStarterCard(Caster.DATA, 4);
        AddStarterCard(Rider.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Saber.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return JeanneDArc.DATA;
    }
}
