package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Trapinch;
import eatyourbeets.cards.animator.series.TenseiSlime.*;
import eatyourbeets.cards.animator.ultrarare.Veldora;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_TenseiSlime extends AnimatorLoadout
{
    public Loadout_TenseiSlime()
    {
        super(CardSeries.TenseiSlime);
        this.EmblemicPokemon = new Trapinch();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Shion.DATA, 7);
        AddStarterCard(Shuna.DATA, 5);
        AddStarterCard(Hakurou.DATA, 8);
        AddStarterCard(Benimaru.DATA, 5);
        AddStarterCard(Gobta.DATA, 6);
        AddStarterCard(GazelDwargon.DATA, 7);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Rimuru.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Veldora.DATA;
    }
}
