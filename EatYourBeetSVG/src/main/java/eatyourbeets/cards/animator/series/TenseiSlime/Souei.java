package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Souei extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Souei.class)
            .SetSkill(3, CardRarity.UNCOMMON, EYBCardTarget.Normal)
            
            .SetSeriesFromClassPackage()
            .ObtainableAsReward((data, deck) -> data.GetTotalCopies(deck) <= 0 || deck.size() >= 24);

    public Souei()
    {
        super(DATA);

        Initialize(0, 0, 8);
        SetUpgrade(0, 0, 8);

        SetAffinity_Green(1);
        SetAffinity_Black(1);
        SetAffinity_Violet(1);
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ApplyPoison(TargetHelper.Normal(m), magicNumber);

        GameActions.Bottom.ExhaustFromHand(name, 1, false)
                .SetFilter(GameUtilities::IsHighCost)
                .AddCallback(() ->
        {
            final int intangible = GameUtilities.GetPowerAmount(IntangiblePlayerPower.POWER_ID);
            for (AbstractMonster enemy : GameUtilities.GetEnemies(true))
            {
                PoisonPower poison = GameUtilities.GetPower(enemy, PoisonPower.class);
                if (poison != null)
                {
                    GameActions.Top.Callback(new PoisonLoseHpAction(enemy, player, poison.amount, AttackEffects.POISON))
                    .AddCallback(intangible, (baseIntangible, action) ->
                    {
                        if (GameUtilities.IsFatal(action.target, true))
                        {
                            if (GameUtilities.GetPowerAmount(IntangiblePlayerPower.POWER_ID) == baseIntangible)
                            {
                                GameActions.Top.StackPower(new IntangiblePlayerPower(player, 1));
                            }
                        }
                    });
                }
            }
        });
    }
}