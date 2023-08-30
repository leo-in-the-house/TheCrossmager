package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.AngelBeats.*;
import eatyourbeets.cards.animator.ultrarare.AngelAlter;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_AngelBeats extends AnimatorLoadout
{
    public Loadout_AngelBeats()
    {
        super(CardSeries.AngelBeats);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Godan.DATA, 7);
        AddStarterCard(HidekiHinata.DATA, 7);
        AddStarterCard(MasamiIwasawa.DATA, 5);
        AddStarterCard(Noda.DATA, 7);
        AddStarterCard(Yusa.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return KanadeTachibana.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return AngelAlter.DATA;
    }
}
