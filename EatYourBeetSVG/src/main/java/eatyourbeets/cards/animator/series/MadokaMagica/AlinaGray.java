package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.NoxiousFumesPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnAfterCardExhaustedSubscriber;
import eatyourbeets.interfaces.subscribers.OnAfterCardPurgedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AlinaGray extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AlinaGray.class)
            .SetPower(2, CardRarity.RARE)
            
            .SetSeriesFromClassPackage();

    public AlinaGray()
    {
        super(DATA);

        Initialize(0, 0, 3, 0);
        SetUpgrade(0, 6, 1, 0);

        SetAffinity_Violet(2);
        SetAffinity_Blue(1);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        AddScaling(Affinity.Violet, 1);
        AddScaling(Affinity.Blue, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        if (upgraded) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.StackPower(new AlinaGrayPower(p, magicNumber));
    }

    public static class AlinaGrayPower extends AnimatorPower implements OnAfterCardExhaustedSubscriber, OnAfterCardPurgedSubscriber
    {
        public AlinaGrayPower(AbstractCreature owner, int amount)
        {
            super(owner, AlinaGray.DATA);

            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAfterCardExhausted.Subscribe(this);
            CombatStats.onAfterCardPurged.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAfterCardExhausted.Unsubscribe(this);
            CombatStats.onAfterCardPurged.Unsubscribe(this);
        }

        @Override
        public void OnAfterCardExhausted(AbstractCard card)
        {
            if (GameUtilities.IsDeadOrEscaped(owner))
            {
                CombatStats.onAfterCardExhausted.Unsubscribe(this);
                CombatStats.onAfterCardPurged.Unsubscribe(this);
                return;
            }

            if (amount > 0 && card.type == CardType.CURSE) {
                GameActions.Bottom.StackPower(owner, new NoxiousFumesPower(owner, 1));
                flashWithoutSound();
                reducePower(1);

                if (amount <= 0) {
                    SetEnabled(false);
                    RemovePower();
                    flash();
                }
            }
        }

        @Override
        public void OnAfterCardPurged(AbstractCard card)
        {
            if (GameUtilities.IsDeadOrEscaped(owner))
            {
                CombatStats.onAfterCardExhausted.Unsubscribe(this);
                CombatStats.onAfterCardPurged.Unsubscribe(this);
                return;
            }

            if (amount > 0 && card.type == CardType.CURSE) {
                GameActions.Bottom.StackPower(owner, new NoxiousFumesPower(owner, 1));
                flashWithoutSound();
                reducePower(1);

                if (amount <= 0) {
                    SetEnabled(false);
                    RemovePower();
                    flash();
                }
            }
        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(0, amount);
        }

        @Override
        public void onChannel(AbstractOrb orb)
        {
            super.onChannel(orb);
        }
    }
}