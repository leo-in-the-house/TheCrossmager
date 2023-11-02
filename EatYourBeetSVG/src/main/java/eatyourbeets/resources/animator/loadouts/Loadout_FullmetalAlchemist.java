package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.FullmetalAlchemist.*;
import eatyourbeets.cards.animator.ultrarare.Truth;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_FullmetalAlchemist extends AnimatorLoadout
{
    public Loadout_FullmetalAlchemist()
    {
        super(CardSeries.FullmetalAlchemist);
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(MaesHughes.DATA, 5);
        AddStarterCard(Scar.DATA, 7);
        AddStarterCard(ElricAlphonse.DATA, 5);
        AddStarterCard(Sloth.DATA, 8);
        AddStarterCard(Lust.DATA, 7);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return RoyMustang.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Truth.DATA;
    }
}
