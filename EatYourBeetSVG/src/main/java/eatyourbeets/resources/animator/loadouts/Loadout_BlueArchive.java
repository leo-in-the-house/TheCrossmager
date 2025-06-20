package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Wattrel;
import eatyourbeets.cards.animator.series.BlueArchive.*;
import eatyourbeets.cards.animator.ultrarare.YumeKuchinashi;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_BlueArchive extends AnimatorLoadout
{
    public Loadout_BlueArchive()
    {
        super(CardSeries.BlueArchive);
        this.EmblemicPokemon = new Wattrel();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(HifumiAjitani.DATA, 5);
        AddStarterCard(AliceTendou.DATA, 8);
        AddStarterCard(AruRikuhachima.DATA, 7);
        AddStarterCard(AsunaIchinose.DATA, 4);
        AddStarterCard(HikariTachibana.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return HoshinoTakanashi.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return YumeKuchinashi.DATA;
    }
}
