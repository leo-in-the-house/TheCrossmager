package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.basic.seriespokemon.Mimikyu;
import eatyourbeets.cards.animator.series.MadokaMagica.*;
import eatyourbeets.cards.animator.ultrarare.Walpurgisnacht;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTooltip;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_MadokaMagica extends AnimatorLoadout
{
    private static final EYBCardTooltip witchTooltip = new EYBCardTooltip("", "");

    public Loadout_MadokaMagica()
    {
        super(CardSeries.MadokaMagica);
        this.EmblemicPokemon = new Mimikyu();
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(SanaFutaba.DATA, 6);
        AddStarterCard(IrohaTamaki.DATA, 7);
        AddStarterCard(YuiTsuruno.DATA, 8);
        AddStarterCard(FeliciaMitsuki.DATA, 7);
        AddStarterCard(MifuyuAzusa.DATA, 5);
        AddStarterCard(SuzuneAmano.DATA, 8);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return MadokaKaname.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Walpurgisnacht.DATA;
    }
}
