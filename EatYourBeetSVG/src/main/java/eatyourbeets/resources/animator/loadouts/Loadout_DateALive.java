package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Ralts;
import eatyourbeets.cards.animator.series.DateALive.*;
import eatyourbeets.cards.animator.ultrarare.MioTakamiya;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_DateALive extends AnimatorLoadout
{
    public Loadout_DateALive()
    {
        super(CardSeries.DateALive);
        this.EmblemicPokemon = new Ralts();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Mayuri.DATA, 7);
        AddStarterCard(YamaiSisters.DATA, 7);
        AddStarterCard(MikuIzayoi.DATA, 5);
        AddStarterCard(NiaHonjou.DATA, 5);
        AddStarterCard(ShidoItsuka.DATA, 6);
        AddStarterCard(RioSonogami.DATA, 6);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return TohkaYatogami.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return MioTakamiya.DATA;
    }
}
