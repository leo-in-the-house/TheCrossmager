package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardEffectChoice;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_EnterStance;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Henrietta extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Henrietta.class)
            .SetPower(3, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Henrietta()
    {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Pink(1);
        SetAffinity_Yellow(1);
        SetAffinity_Teal(1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        if (!GameUtilities.InStance(NeutralStance.STANCE_ID))
        {
            GameActions.Bottom.GainEnergy(1);
        }

        GameActions.Bottom.StackPower(new HenriettaPower(p, secondaryValue, this));
    }

    public static class HenriettaPower extends AnimatorClickablePower
    {
        private static final CardEffectChoice choices = new CardEffectChoice();
        private final Henrietta sourceCard;

        public HenriettaPower(AbstractPlayer owner, int amount, Henrietta sourceCard)
        {
            super(owner, Henrietta.DATA, PowerTriggerConditionType.Energy, 1);

            this.triggerCondition.SetUses(1, true, true);
            this.sourceCard = sourceCard;

            Initialize(amount);
        }

        @Override
        public void onAfterCardPlayed(AbstractCard card) {
            super.onAfterCardPlayed(card);

            if (GameUtilities.IsDelayed(card) && sourceCard != null) {
                if (choices.TryInitialize(sourceCard))
                {
                    choices.AddEffect(new GenericEffect_EnterStance(WrathStance.STANCE_ID));
                    choices.AddEffect(new GenericEffect_EnterStance(TranceStance.STANCE_ID));
                    choices.AddEffect(new GenericEffect_EnterStance(MagicStance.STANCE_ID));
                    choices.AddEffect(new GenericEffect_EnterStance(CalmStance.STANCE_ID));
                    choices.AddEffect(new GenericEffect_EnterStance(NeutralStance.STANCE_ID));
                    choices.Initialize(sourceCard);
                }

                choices.Select(1, null);
            }
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            playApplyPowerSfx();

            GameActions.Bottom.Draw(1)
            .SetFilter(GameUtilities::IsDelayed, false);
        }
    }
}