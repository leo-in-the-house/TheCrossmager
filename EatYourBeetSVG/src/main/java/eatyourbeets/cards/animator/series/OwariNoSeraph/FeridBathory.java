package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.vfx.megacritCopy.HemokinesisEffect2;
import eatyourbeets.interfaces.subscribers.OnAfterCardExhaustedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.*;

public class FeridBathory extends AnimatorCard
{
    public static final EYBCardData DATA = Register(FeridBathory.class)
            .SetPower(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public FeridBathory()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Black(2);
        SetAffinity_Violet(2);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(p, new FeridBathoryPower(p, magicNumber));
    }

    public static class FeridBathoryPower extends AnimatorPower implements OnAfterCardExhaustedSubscriber
    {
        public FeridBathoryPower(AbstractCreature source, int amount)
        {
            super(source, FeridBathory.DATA);

            Initialize(amount, PowerType.BUFF, false);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAfterCardExhausted.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAfterCardExhausted.Unsubscribe(this);
        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(0, amount);
        }

        @Override
        public void OnAfterCardExhausted(AbstractCard card)
        {
            GameActions.Bottom.DealDamage(source, GameUtilities.GetRandomEnemy(true), amount, DamageInfo.DamageType.HP_LOSS, AttackEffects.NONE)
            .SetDamageEffect(enemy ->
            {
                GameEffects.List.Add(new HemokinesisEffect2(enemy.hb.cX, enemy.hb.cY, source.hb.cX, source.hb.cY));
                return 0f;
            });
            GameActions.Bottom.GainTemporaryHP(amount);
            flashWithoutSound();
        }
    }
}