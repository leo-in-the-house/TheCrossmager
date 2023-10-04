package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.GenshinImpact.*;
import eatyourbeets.cards.animator.ultrarare.Traveler;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_GenshinImpact extends AnimatorLoadout
{
    public Loadout_GenshinImpact()
    {
        super(CardSeries.GenshinImpact);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Amber.DATA, 7);
        AddStarterCard(HuTao.DATA, 5);
        AddStarterCard(Noelle.DATA, 7);
        AddStarterCard(Nilou.DATA, 6);
        AddStarterCard(Dehya.DATA, 7);
        AddStarterCard(Dori.DATA, 8);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Keqing.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Traveler.DATA;
    }
}
