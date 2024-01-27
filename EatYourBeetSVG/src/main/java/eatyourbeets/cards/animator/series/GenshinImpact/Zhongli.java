package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.orbs.animator.Earth;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;
import eatyourbeets.utilities.ListSelection;

public class Zhongli extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Zhongli.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage();
    private static final int POWER_ENERGY_COST = 1;

    public Zhongli()
    {
        super(DATA);

        Initialize(0, 0, 3, 1);
        SetUpgrade(0, 0, 4, 1);

        SetAffinity_Brown(2);
        SetAffinity_Black(1);

        SetEthereal(true);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return (magicNumber > 0) ? TempHPAttribute.Instance.SetCard(this, true) : null;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainTemporaryHP(magicNumber);
        GameActions.Bottom.GainOrbSlots(secondaryValue);
        GameActions.Bottom.StackPower(new ZhongliPower(p, 1));
    }

    public static class ZhongliPower extends AnimatorClickablePower
    {
        public ZhongliPower(AbstractPlayer owner, int amount)
        {
            super(owner, Zhongli.DATA, PowerTriggerConditionType.Energy, POWER_ENERGY_COST);

            this.triggerCondition.SetUses(amount, true, true);
            canBeZero = true;
            Initialize(amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            GameActions.Bottom.ChannelOrb(new Earth());
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, amount);
        }

        @Override
        public void onEvokeOrb(AbstractOrb orb) {

            super.onEvokeOrb(orb);

            Earth earthOrb = JUtils.SafeCast(orb, Earth.class);

            if (earthOrb != null) {
                GameActions.Bottom.RemoveDebuffs(player, ListSelection.Default(0), player.powers.size());
            }
        }
    }
}