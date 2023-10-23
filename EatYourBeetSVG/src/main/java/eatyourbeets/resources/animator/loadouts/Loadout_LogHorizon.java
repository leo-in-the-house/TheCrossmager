package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.ultrarare.Kanami;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.animator.series.LogHorizon.*;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_LogHorizon extends AnimatorLoadout
{
    public Loadout_LogHorizon()
    {
        super(CardSeries.LogHorizon);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(HousakiTohya.DATA, 7);
        AddStarterCard(HousakiMinori.DATA, 5);
        AddStarterCard(IsuzuTonan.DATA, 6);
        AddStarterCard(Serara.DATA, 5);
        AddStarterCard(Krusty.DATA, 7);
        AddStarterCard(Naotsugu.DATA, 8);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Akatsuki.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Kanami.DATA;
    }
}
