package eatyourbeets.cards.effects.VestaElixirEffects;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;

public class VestaElixirEffect_Affinity_Star extends VestaElixirEffect
{
    public VestaElixirEffect_Affinity_Star(boolean upgraded)
    {
        super(upgraded ? 2 : 1);
    }

    @Override
    public String GetDescription()
    {
        return ACTIONS.GainAmount(amount, GR.AnimatorClassic.IsSelected() ? GR.Tooltips.GreenThreshold : GR.Tooltips.Affinity_Star, true);
    }

    @Override
    public void EnqueueAction(EYBCard elixir, AbstractPlayer player)
    {
        for (Affinity affinity: Affinity.Basic()) {
            GameActions.Bottom.GainAffinity(affinity, amount);
        }
    }
}