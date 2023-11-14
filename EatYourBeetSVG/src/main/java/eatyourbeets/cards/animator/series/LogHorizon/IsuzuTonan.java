package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.common.DeEnergizedPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class IsuzuTonan extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IsuzuTonan.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    private static final CardEffectChoice choices = new CardEffectChoice();

    public IsuzuTonan()
    {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Yellow(2);

        SetExhaust(true);
        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        int energyGainAmount = player.hand.group.size();

        if (energyGainAmount > 0) {
            GameActions.Bottom.GainEnergy(energyGainAmount);
            GameActions.Bottom.StackPower(new DeEnergizedPower(player, energyGainAmount));
        }
    }
}