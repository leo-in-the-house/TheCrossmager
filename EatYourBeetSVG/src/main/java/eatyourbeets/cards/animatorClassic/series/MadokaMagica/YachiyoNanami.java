package eatyourbeets.cards.animatorClassic.series.MadokaMagica;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardEffectChoice;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_EnterStance;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.utilities.GameActions;

public class YachiyoNanami extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(YachiyoNanami.class).SetSeriesFromClassPackage().SetPower(2, CardRarity.UNCOMMON);

    private static final CardEffectChoice choices = new CardEffectChoice();

    public YachiyoNanami()
    {
        super(DATA);

        Initialize(0, 0, YachiyoNanamiPower.BLOCK_AMOUNT);
        SetEthereal(true);

        
        
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new YachiyoNanamiPower(p, 1));

        if (info.IsSynergizing)
        {
            if (choices.TryInitialize(this))
            {
                choices.AddEffect(new GenericEffect_EnterStance(AgilityStance.STANCE_ID));
                choices.AddEffect(new GenericEffect_EnterStance(IntellectStance.STANCE_ID));
            }

            choices.Select(1, m).CancellableFromPlayer(true);
        }
    }

    public static class YachiyoNanamiPower extends AnimatorPower
    {
        public static final int BLOCK_AMOUNT = 5;

        public YachiyoNanamiPower(AbstractPlayer owner, int amount)
        {
            super(owner, YachiyoNanami.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount, BLOCK_AMOUNT);
        }

        @Override
        public void atStartOfTurnPostDraw()
        {
            flash();

            GameActions.Bottom.DiscardFromHand(name, amount, false)
            .SetOptions(true, true, true)
            .AddCallback(cards ->
            {
                for (AbstractCard card : cards)
                {
                    GameActions.Bottom.GainBlock(BLOCK_AMOUNT);
                }
            });
        }
    }
}