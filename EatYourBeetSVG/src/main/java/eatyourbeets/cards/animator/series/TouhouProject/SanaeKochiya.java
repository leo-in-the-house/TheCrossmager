package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Special_Miracle;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SanaeKochiya extends AnimatorCard {
    public static final EYBCardData DATA = Register(SanaeKochiya.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Special_Miracle(), false));


    public SanaeKochiya()
    {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 2, 2);

        SetAffinity_White(2, 0, 2);
        SetAffinity_Green(1);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        if (upgraded) {
            return super.GetBlockInfo().AddMultiplier(magicNumber);
        }

        return super.GetBlockInfo();
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        if (upgraded) {
            for (int i=0; i<magicNumber; i++) {
                GameActions.Bottom.GainBlock(block);
            }
        }

        GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
        GameActions.Bottom.StackPower(new SanaeKochiyaPower(player, 1, upgraded));
    }

    public static class SanaeKochiyaPower extends AnimatorPower implements OnBlockGainedSubscriber {

        private boolean upgraded;
        public SanaeKochiyaPower(AbstractCreature owner, int amount, boolean upgraded) {
            super(owner, SanaeKochiya.DATA);

            this.upgraded = upgraded;

            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(upgraded ? 1 : 0, amount);
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
            if (block > 0) {
                for (int i=0; i<amount; i++) {
                    GameActions.Bottom.MakeCardInDrawPile(new Miracle())
                        .AddCallback(card -> {
                            if (upgraded && card instanceof EYBCard) {
                                ((EYBCard) card).SetHaste(true);
                            }
                        });
                }
            }
        }
    }
}