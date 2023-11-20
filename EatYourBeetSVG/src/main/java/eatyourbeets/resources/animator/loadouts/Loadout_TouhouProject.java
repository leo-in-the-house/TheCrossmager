package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.TouhouProject.*;
import eatyourbeets.cards.animator.ultrarare.YuyukoSaigyouji;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_TouhouProject extends AnimatorLoadout
{
    public Loadout_TouhouProject()
    {
        super(CardSeries.TouhouProject);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(ReimuHakurei.DATA, 8);
        AddStarterCard(MarisaKirisame.DATA, 7);
        AddStarterCard(ReisenInaba.DATA, 5);
        AddStarterCard(Cirno.DATA, 7);
        AddStarterCard(Clownpiece.DATA, 6);
        AddStarterCard(AunnKomano.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return ReimuHakurei.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return YuyukoSaigyouji.DATA;
    }
}