package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.Bleach.*;
import eatyourbeets.cards.animator.ultrarare.SosukeAizen;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Bleach extends AnimatorLoadout
{
    public Loadout_Bleach()
    {
        super(CardSeries.Bleach);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(IkkakuMadarame.DATA, 7);
        AddStarterCard(RenjiAbarai.DATA, 7);
        AddStarterCard(OrihimeInoue.DATA, 5);
        AddStarterCard(YasutoraSado.DATA, 7);
        AddStarterCard(MayuriKurotsuchi.DATA, 5);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return IchigoKurosaki.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return SosukeAizen.DATA;
    }
}
