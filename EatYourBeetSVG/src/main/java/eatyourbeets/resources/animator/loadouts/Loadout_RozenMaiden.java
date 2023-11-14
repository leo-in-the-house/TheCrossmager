package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.RozenMaiden.*;
import eatyourbeets.cards.animator.ultrarare.Kirakishou;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_RozenMaiden extends AnimatorLoadout
{
    public Loadout_RozenMaiden()
    {
        super(CardSeries.RozenMaiden);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Hinaichigo.DATA, 5);
        AddStarterCard(Souseiseki.DATA, 8);
        AddStarterCard(Suiseiseki.DATA, 6);
        AddStarterCard(TomoeKashiwaba.DATA, 7);
        AddStarterCard(NoriSakurada.DATA, 6);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Suigintou.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Kirakishou.DATA;
    }
}
