package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RukiaBankai extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RukiaBankai.class).SetSkill(-1, CardRarity.SPECIAL, EYBCardTarget.None).SetSeries(CardSeries.Bleach);

    public RukiaBankai()
    {
        super(DATA);

        Initialize(0, 0, 3, 3);
        SetUpgrade(0, 0, 0, 3);

        SetAffinity_Red(1);
        SetAffinity_Blue(1);

        SetMultiDamage(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        int stacks = GameUtilities.UseXCostEnergy(this);

        GameActions.Bottom.GainOrbSlots(magicNumber);

        for (int i=0; i<stacks; i++) {
            Frost frost = new Frost();
            GameUtilities.IncreaseOrbPassiveAmount(frost, secondaryValue);
            GameActions.Bottom.ChannelOrb(frost);
        }
    }
}