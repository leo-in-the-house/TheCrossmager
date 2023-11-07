package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.Elsword.*;
import eatyourbeets.cards.animator.ultrarare.Rose;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Elsword extends AnimatorLoadout
{
    public Loadout_Elsword()
    {
        super(CardSeries.Elsword);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Elsword.DATA, 7);
        AddStarterCard(Ciel.DATA, 5);
        AddStarterCard(Chung.DATA, 6);
        AddStarterCard(Raven.DATA, 8);
        AddStarterCard(Ara.DATA, 7);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Eve.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Rose.DATA;
    }
}
