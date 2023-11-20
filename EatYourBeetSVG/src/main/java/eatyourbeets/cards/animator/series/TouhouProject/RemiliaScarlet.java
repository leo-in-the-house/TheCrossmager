package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.FlandreScarlet;
import eatyourbeets.cards.animator.special.SakuyaIzayoi;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.stances.CorruptionStance;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RemiliaScarlet extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RemiliaScarlet.class)
            .SetPower(1, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.TouhouProject_Remilia(FlandreScarlet.DATA, 3));
                data.AddPreview(new SakuyaIzayoi(), true);
                data.AddPreview(new FlandreScarlet(), false);
            });

    public RemiliaScarlet()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Red(1);
        SetAffinity_Black(1);

        SetEthereal(true);
        SetDelayed(true);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (startOfBattle) {
            GameActions.Bottom.MakeCardInDrawPile(new SakuyaIzayoi())
                  .SetUpgrade(upgraded, true);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new RemiliaScarletPower(p, 1, magicNumber));
    }

    public static class RemiliaScarletPower extends AnimatorClickablePower
    {
        public RemiliaScarletPower(AbstractCreature owner, int amount, int useAmount)
        {
            super(owner, RemiliaScarlet.DATA, PowerTriggerConditionType.Special, 0, RemiliaScarletPower::CheckCondition, __ -> {});

            triggerCondition.SetUses(useAmount, false, true);

            Initialize(amount);
        }

        private static boolean CheckCondition(int cost)
        {
            return player.hand.size() > 0;
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            GameActions.Bottom.ExhaustFromHand(name, 1, false)
                .AddCallback(cards -> {
                    int hpToGain = 0;

                    for(AbstractCard card : cards) {
                        if (GameUtilities.HasAttackMultiplier(card)) {
                            hpToGain += GameUtilities.GetAttackMultiplier(card);
                        }
                        if (GameUtilities.HasBlockMultiplier(card)) {
                            hpToGain += GameUtilities.GetBlockMultiplier(card);
                        }
                    }

                    if (hpToGain > 0) {
                        GameActions.Top.Heal(hpToGain);
                    }
                });
        }

        @Override
        public void atStartOfTurnPostDraw()
        {
            super.atStartOfTurnPostDraw();

            if (AgilityStance.IsActive())
            {
                GameActions.Bottom.GainGreen(1, true);
            }
            else if (WrathStance.IsActive())
            {
                GameActions.Bottom.GainRed(1, true);
            }
            else if (IntellectStance.IsActive())
            {
                GameActions.Bottom.GainBlue(1, true);
            }
            else if (CorruptionStance.IsActive())
            {
                GameActions.Bottom.GainBlack(1, true);
            }
            else
            {
                GameActions.Bottom.GainRandomAffinityPower(1, true);
            }

            flash();
        }
    }
}