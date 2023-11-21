package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.interfaces.subscribers.OnAttackSubscriber;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ByakurenHijiri extends AnimatorCard {
    public static final EYBCardData DATA = Register(ByakurenHijiri.class)
            .SetPower(4, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public ByakurenHijiri() {
        super(DATA);

        Initialize(0, 2, 4, 2);
        SetUpgrade(0, 3, 0);

        SetAffinity_Blue(1);
        SetAffinity_Black(1);
        SetAffinity_White(1);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.StackPower(new ByakurenHijiriPower(p, secondaryValue));
    }

    public static class ByakurenHijiriPower extends AnimatorPower implements OnAttackSubscriber, OnBlockGainedSubscriber {
        public ByakurenHijiriPower(AbstractCreature owner, int amount) {
            super(owner, ByakurenHijiri.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }


        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            CombatStats.onAttack.Subscribe(this);
            CombatStats.onBlockGained.Subscribe(this);
        }

        @Override
        public void onRemove() {
            super.onRemove();

            CombatStats.onAttack.Unsubscribe(this);
            CombatStats.onBlockGained.Unsubscribe(this);
        }

        @Override
        public void OnAttack(DamageInfo info, int damageAmount, AbstractCreature target)
        {
            if (info.type == DamageInfo.DamageType.NORMAL && GameUtilities.IsMonster(target))
            {
                GameActions.Bottom.GainTemporaryHP(amount);
            }
        }

        @Override
        public void OnBlockGained(AbstractCreature creature, int block)
        {
            if (this.amount > 0) {
                GameActions.Bottom.GainBlue(1);
                GameActions.Bottom.GainBlack(1);
                GameActions.Bottom.GainWhite(1);
            }
        }
    }
}