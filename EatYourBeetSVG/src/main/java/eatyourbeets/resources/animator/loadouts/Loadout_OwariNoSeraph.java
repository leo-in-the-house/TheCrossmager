package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.OwariNoSeraph.*;
import eatyourbeets.cards.animator.ultrarare.HiiragiTenri;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_OwariNoSeraph extends AnimatorLoadout
{
    public Loadout_OwariNoSeraph()
    {
        super(CardSeries.OwariNoSeraph);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Shinoa.DATA, 6);
        AddStarterCard(Shigure.DATA, 7);
        AddStarterCard(Mitsuba.DATA, 7);
        AddStarterCard(SayuriHanayori.DATA, 6);
        AddStarterCard(Yoichi.DATA, 5);
        AddStarterCard(Mikaela.DATA, 8);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Yuuichirou.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return HiiragiTenri.DATA;
    }
}
