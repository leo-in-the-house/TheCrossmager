package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.Rewrite.*;
import eatyourbeets.cards.animator.ultrarare.SakuraKashima;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Rewrite extends AnimatorLoadout
{
    public Loadout_Rewrite()
    {
        super(CardSeries.Rewrite);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Luis.DATA, 7);
        AddStarterCard(AkiraInoue.DATA, 5);
        AddStarterCard(ShizuruNakatsu.DATA, 6);
        AddStarterCard(YoshinoHaruhiko.DATA, 8);
        AddStarterCard(Midou.DATA, 7);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return KotoriKanbe.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return SakuraKashima.DATA;
    }
}
