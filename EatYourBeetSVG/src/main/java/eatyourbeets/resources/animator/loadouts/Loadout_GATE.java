package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Rockruff;
import eatyourbeets.cards.animator.series.GATE.*;
import eatyourbeets.cards.animator.ultrarare.Giselle;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_GATE extends AnimatorLoadout
{
    public Loadout_GATE()
    {
        super(CardSeries.GATE);
        this.EmblemicPokemon = new Rockruff();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(MariKurokawa.DATA, 5);
        AddStarterCard(YaoHaDucy.DATA, 7);
        AddStarterCard(Kuribayashi.DATA, 6);
        AddStarterCard(ShunyaKengun.DATA, 7);
        AddStarterCard(TukaLunaMarceau.DATA, 5);
        AddStarterCard(Tyuule.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return RoryMercury.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Giselle.DATA;
    }
}
