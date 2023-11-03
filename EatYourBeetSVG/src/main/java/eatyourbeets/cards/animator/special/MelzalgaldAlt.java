package eatyourbeets.cards.animator.special;

import eatyourbeets.cards.animator.series.OnePunchMan.Melzalgald;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;

public abstract class MelzalgaldAlt extends AnimatorCard
{
    protected final static CardSeries SERIES = Melzalgald.DATA.Series;

    public MelzalgaldAlt(EYBCardData data)
    {
        super(data);

        Initialize(6, 0, 3);
        SetUpgrade(3, 0, 1);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }


}