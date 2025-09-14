package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Rolycoly;
import eatyourbeets.cards.animator.series.LegendOfHeroesTrails.*;
import eatyourbeets.cards.animator.ultrarare.Grandmaster;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_LegendOfHeroesTrails extends AnimatorLoadout
{
    public Loadout_LegendOfHeroesTrails()
    {
        super(CardSeries.LegendOfHeroesTrails);
        this.EmblemicPokemon = new Rolycoly();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(SwinAbel.DATA, 7);
        AddStarterCard(NadiaRayne.DATA, 6);
        AddStarterCard(ShizunaRemMitsurugi.DATA, 8);
        AddStarterCard(TitaRussell.DATA, 5);
        AddStarterCard(AltinaOrion.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return EstelleBright.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Grandmaster.DATA;
    }
}
