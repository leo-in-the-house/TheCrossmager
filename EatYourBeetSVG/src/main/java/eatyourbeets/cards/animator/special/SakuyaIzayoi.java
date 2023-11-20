package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.TouhouProject.RemiliaScarlet;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SakuyaIzayoi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SakuyaIzayoi.class)
            .SetSkill(3, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(RemiliaScarlet.DATA.Series)
            .PostInitialize(data ->
            {
                for (ThrowingKnife knife : ThrowingKnife.GetAllCards())
                {
                    data.AddPreview(knife, false);
                }
            });

    public SakuyaIzayoi()
    {
        super(DATA);

        Initialize(0, 2, 12, 4);
        SetUpgrade(0, 1);

        SetAffinity_Green(1);
        SetAffinity_Black(1);
        SetAffinity_Teal(1);

        SetFading(true);
        SetDelayed(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }
    }

    @Override
    public void triggerOnManualDiscard() {
        super.triggerOnManualDiscard();

        GameActions.Bottom.StackPower(new SakuyaIzayoiPower(player, secondaryValue));
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();

        GameActions.Bottom.StackPower(new SakuyaIzayoiPower(player, secondaryValue));
    }

    public static class SakuyaIzayoiPower extends AnimatorPower implements OnBlockGainedSubscriber
    {
        public SakuyaIzayoiPower(AbstractCreature owner, int amount)
        {
            super(owner, SakuyaIzayoi.DATA);

            Initialize(amount);
        }


        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            CombatStats.onBlockGained.Subscribe(this);
        }

        @Override
        public void onRemove() {
            super.onRemove();

            CombatStats.onBlockGained.Unsubscribe(this);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            SetEnabled(false);
            RemovePower();
            flash();
        }

        @Override
        public void OnBlockGained(AbstractCreature creature, int block)
        {
            GameActions.Bottom.Callback(() -> {
                if (this.amount > 0) {
                    GameActions.Top.CreateThrowingKnives(1);
                    ReducePower(1);
                }
            });
        }
    }
}