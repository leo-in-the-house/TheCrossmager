package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.Atelier.*;
import eatyourbeets.cards.animator.ultrarare.PamelaIbis;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Atelier extends AnimatorLoadout
{
    public Loadout_Atelier()
    {
        super(CardSeries.Atelier);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Ryza.DATA, 7);
        AddStarterCard(AyeshaAltugle.DATA, 6);
        AddStarterCard(EschaMalier.DATA, 5);
        AddStarterCard(KlaudiaValentz.DATA, 5);
        AddStarterCard(Viorate.DATA, 8);
        AddStarterCard(FirisMistlud.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return SophieNeuenmuller.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return PamelaIbis.DATA;
    }
}
