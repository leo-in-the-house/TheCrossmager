package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ReineMurasame extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ReineMurasame.class)
            .SetSkill(-1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new eatyourbeets.cards.animator.series.DateALive.ShidoItsuka(), true);
    }

    public ReineMurasame()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 0);

        SetExhaust(true);

        SetAffinity_Blue(2);

        SetAffinityRequirement(Affinity.Blue, 5);
        
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        int stacks = GameUtilities.UseXCostEnergy(this);

        for (int i = 0; i < stacks; i++)
        {
            GameActions.Bottom.MakeCardInDrawPile(new eatyourbeets.cards.animator.series.DateALive.ShidoItsuka())
            .SetUpgrade(upgraded, true)
            .AddCallback(card -> {
                if (card.costForTurn > 0)
                {
                    final String key = cardID + uuid;

                    CostModifiers.For(card).Add(key, -1);

                    GameUtilities.TriggerWhenPlayed(card, key, (k, c) ->
                    {
                        CostModifiers.For(c).Remove(k, false);
                    });
                }
            });
        }

        if (CheckSpecialCondition(false) && stacks > 0)
        {
            GameActions.Bottom.StackPower(new DrawCardNextTurnPower(p, stacks));
        }
    }
}