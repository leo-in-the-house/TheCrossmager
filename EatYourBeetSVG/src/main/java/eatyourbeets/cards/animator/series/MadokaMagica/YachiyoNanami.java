package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YachiyoNanami extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YachiyoNanami.class)
            .SetPower(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public YachiyoNanami()
    {
        super(DATA);

        Initialize(0, 0, 8);
        SetUpgrade(0, 0, 8);

        SetEthereal(true);

        SetAffinity_Blue(2);
        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainOrbSlots(1);
        GameActions.Bottom.ChannelOrb(new Water());
        GameActions.Bottom.StackPower(new YachiyoNanamiPower(p, magicNumber));
    }

    public static class YachiyoNanamiPower extends AnimatorClickablePower
    {
        public YachiyoNanamiPower(AbstractPlayer owner, int amount)
        {
            super(owner, YachiyoNanami.DATA, PowerTriggerConditionType.Special, 0, YachiyoNanamiPower::CheckCondition, __ -> {});

            triggerCondition.SetUses(1, true, true);

            Initialize(amount);
        }

        private static boolean CheckCondition(int cost)
        {
            return player.drawPile.getCardsOfType(CardType.CURSE).size() > 0 || player.hand.getCardsOfType(CardType.CURSE).size() > 0;
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            GameActions.Bottom.ExhaustFromPile(name, 1, player.hand, player.drawPile)
                .SetOptions(false, false, false)
                .SetFilter(card -> card.type == CardType.CURSE)
                .AddCallback(cards -> {
                    if (cards.size() > 0) {
                        GameActions.Top.GainBlock(amount);
                    }
                });
        }
    }
}