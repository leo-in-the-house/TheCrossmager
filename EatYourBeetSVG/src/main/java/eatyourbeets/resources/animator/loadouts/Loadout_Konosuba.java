package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.Konosuba.*;
import eatyourbeets.cards.animator.ultrarare.Chomusuke;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTooltip;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Konosuba extends AnimatorLoadout
{
    private static final EYBCardTooltip sylviaTooltip = new EYBCardTooltip("", "");

    public Loadout_Konosuba()
    {
        super(CardSeries.Konosuba);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Mitsurugi.DATA, 7);
        AddStarterCard(Kazuma.DATA, 5);
        AddStarterCard(Verdia.DATA, 6);
        AddStarterCard(Vanir.DATA, 8);
        AddStarterCard(Chris.DATA, 8);
        AddStarterCard(YunYun.DATA, 7);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Megumin.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Chomusuke.DATA;
    }
}
