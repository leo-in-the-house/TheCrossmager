package eatyourbeets.cards.animator.special;

import eatyourbeets.cards.animator.series.MadokaMagica.NagisaMomoe;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;

public class NagisaMomoe_Charlotte extends AnimatorCard
{
    public static final EYBCardData DATA = Register(NagisaMomoe_Charlotte.class)
            .SetCurse(2, EYBCardTarget.None, true)
            .SetSeries(NagisaMomoe.DATA.Series)
            .PostInitialize(data -> data.AddPreview(new NagisaMomoe_CharlotteAlt(), true));

    public NagisaMomoe_Charlotte()
    {
        super(DATA);

        Initialize(0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(1);
        SetAffinity_Black(1);

        SetAutoplayed(true);
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Bottom.MakeCardInDrawPile(new NagisaMomoe_CharlotteAlt()).SetUpgrade(upgraded, false);
    }
}