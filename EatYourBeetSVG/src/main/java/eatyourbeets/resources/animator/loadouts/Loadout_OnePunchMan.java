package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Tyrogue;
import eatyourbeets.cards.animator.series.OnePunchMan.*;
import eatyourbeets.cards.animator.ultrarare.SeriousSaitama;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_OnePunchMan extends AnimatorLoadout
{
    public Loadout_OnePunchMan()
    {
        super(CardSeries.OnePunchMan);
        this.EmblemicPokemon = new Tyrogue();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(King.DATA, 7);
        AddStarterCard(MumenRider.DATA, 7);
        AddStarterCard(MetalBat.DATA, 7);
        AddStarterCard(Genos.DATA, 8);
        AddStarterCard(SilverFang.DATA, 6);
        AddStarterCard(Sonic.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Saitama.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return SeriousSaitama.DATA;
    }
}
