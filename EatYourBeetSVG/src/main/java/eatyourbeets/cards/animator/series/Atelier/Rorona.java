package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.cards.animator.special.Lulua;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.WeightedList;

public class Rorona extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Rorona.class)
            .SetPower(2, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Atelier)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Atelier_Rorona(2, Lulua.DATA));
                data.AddPreview(new Lulua(), true);
            });

    public Rorona()
    {
        super(DATA);

        Initialize(0, 0, 20, 1);
        SetUpgrade(0, 0, 0, 1);

        SetAffinity_White(2);
        SetAffinity_Pink(1);

        SetObtainableInCombat(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new RoronaPower(p, magicNumber, secondaryValue));
    }

    public static class RoronaPower extends AnimatorClickablePower
    {
        private final WeightedList<AbstractCard> toUpgrade = new WeightedList<>();

        int maxUses = 0;

        public RoronaPower(AbstractCreature owner, int cost, int maxUses)
        {
            super(owner, Rorona.DATA, PowerTriggerConditionType.Gold, cost);

            this.amount = cost;
            this.maxUses += amount;
            this.triggerCondition.SetUses(1, false, false);

            Initialize(amount);
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

            GameActions.Bottom.Add(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(false)));
        }


        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            if (this.triggerCondition.uses == 0)
            {
                final int uses = CombatStats.GetCombatData(ID, 1);
                if (uses < maxUses)
                {
                    CombatStats.SetCombatData(ID, uses + 1);
                    this.triggerCondition.AddUses(1);
                }
            }
        }

        @Override
        public AbstractPower makeCopy()
        {
            return new RoronaPower(owner, amount, maxUses);
        }
    }
}