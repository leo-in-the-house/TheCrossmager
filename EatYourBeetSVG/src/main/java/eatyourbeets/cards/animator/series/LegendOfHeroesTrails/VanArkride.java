package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.animator.special.VanArkride_Grendel;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.powers.common.InspirationPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class VanArkride extends AnimatorCard {
    public static final EYBCardData DATA = Register(VanArkride.class)
            .SetPower(2, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new VanArkride_Grendel(), false);
            });

    public VanArkride() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Black(1);
        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new VanArkridePower(p, magicNumber));
    }

    public static class VanArkridePower extends AnimatorClickablePower {
        public VanArkridePower(AbstractCreature owner, int amount) {
            super(owner, VanArkride.DATA, PowerTriggerConditionType.Special, 0, VanArkride.VanArkridePower::CheckCondition, __ -> {});

            this.triggerCondition.SetUses(1, false, true);
            Initialize(amount);
        }

        private static boolean CheckCondition(int cost)
        {
            return GameUtilities.GetPowerAmount(InspirationPower.POWER_ID) >= 6;
        }

        @Override
        public void OnUse(AbstractMonster m) {
            super.OnUse(m);

            int inspirationAmount = GameUtilities.GetPowerAmount(InspirationPower.POWER_ID);

            if (inspirationAmount >= 6) {
                GameActions.Bottom.ReducePower(player, InspirationPower.POWER_ID, 6)
                    .AddCallback(() -> {
                        GameActions.Top.MakeCardInHand(new VanArkride_Grendel())
                            .AddCallback(card -> {
                                if (GameUtilities.GetPowerAmount(InspirationPower.POWER_ID) >= 6) {
                                    GameActions.Bottom.ReducePower(player, InspirationPower.POWER_ID, 6);
                                    card.upgrade();
                                }
                            });
                    });
            }

            RemovePower();
        }


        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            int lightningCount = JUtils.Count(player.orbs, o -> Lightning.ORB_ID.equals(o.ID));
            int inspirationGain = amount + (lightningCount * amount);

            if (inspirationGain > 0) {
                GameActions.Bottom.GainInspiration(inspirationGain);
            }
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0);
        }
    }
}