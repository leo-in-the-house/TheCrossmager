package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.HitsugiNoChaika.*;
import eatyourbeets.cards.animator.ultrarare.NivaLada;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTooltip;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_HitsugiNoChaika extends AnimatorLoadout
{
    private static final EYBCardTooltip tooruTooltip = new EYBCardTooltip("", "");

    public Loadout_HitsugiNoChaika()
    {
        super(CardSeries.HitsugiNoChaika);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(MattheusCallaway.DATA, 5);
        AddStarterCard(ZitaBrusasco.DATA, 4);
        AddStarterCard(Guy.DATA, 5);
        AddStarterCard(Gillette.DATA, 6);
        AddStarterCard(Nikolay.DATA, 7);
        AddStarterCard(ChaikaBohdan.DATA, 8);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return ChaikaTrabant.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return NivaLada.DATA;
    }
}
