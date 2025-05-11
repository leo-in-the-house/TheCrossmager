package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Honedge;
import eatyourbeets.cards.animator.series.Katanagatari.*;
import eatyourbeets.cards.animator.ultrarare.ShikizakiKiki;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Katanagatari extends AnimatorLoadout
{
    public Loadout_Katanagatari()
    {
        super(CardSeries.Katanagatari);
        this.EmblemicPokemon = new Honedge();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(TsurugaMeisai.DATA, 6);
        AddStarterCard(Konayuki.DATA, 5);
        AddStarterCard(ZankiKiguchi.DATA, 6);
        AddStarterCard(UneriGinkaku.DATA, 7);
        AddStarterCard(Azekura.DATA, 7);
        AddStarterCard(ManiwaKyouken.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return HigakiRinne.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return ShikizakiKiki.DATA;
    }
}
