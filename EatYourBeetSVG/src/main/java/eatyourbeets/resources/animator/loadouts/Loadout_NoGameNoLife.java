package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Kricketot;
import eatyourbeets.cards.animator.series.NoGameNoLife.*;
import eatyourbeets.cards.animator.ultrarare.Azriel;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_NoGameNoLife extends AnimatorLoadout
{
    public Loadout_NoGameNoLife()
    {
        super(CardSeries.NoGameNoLife);
        this.EmblemicPokemon = new Kricketot();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(DolaSchwi.DATA, 7);
        AddStarterCard(Miko.DATA, 5);
        AddStarterCard(IzunaHatsuse.DATA, 7);
        AddStarterCard(Jibril.DATA, 7);
        AddStarterCard(DolaCouronne.DATA, 6);
        AddStarterCard(DolaStephanie.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Sora.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Azriel.DATA;
    }
}
