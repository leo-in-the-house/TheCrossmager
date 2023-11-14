package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Enju extends AnimatorCard implements OnAddToDeckListener{
    public static final EYBCardData DATA = Register(Enju.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Enju() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Teal(2);
        SetAffinity_Yellow(2);

        SetObtainableInCombat(false);

        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.PurgeFromPile(name, 1, player.drawPile, player.discardPile)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {{
                    GameActions.Bottom.StackPower(new EnjuPower(p, 1, card));
                }}
            });
    }

    public static class EnjuPower extends AnimatorClickablePower {
        private final AbstractCard purgedCard;
        public EnjuPower(AbstractCreature owner, int amount, AbstractCard card) {
            super(owner, Enju.DATA, PowerTriggerConditionType.Energy, 1);

            triggerCondition.SetUses(1, true, true);
            purgedCard = card;

            Initialize(amount);
            updateDescription();
        }

        @Override
        public void OnUse(AbstractMonster m) {
            super.OnUse(m);

            GameActions.Bottom.MakeCardInDrawPile(purgedCard.makeCopy())
                .SetDestination(CardSelection.Top)
                .AddCallback(card -> {
                    GameUtilities.ModifyCostForCombat(card, 0, false);
                    card.selfRetain = true;
                });
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, purgedCard != null ? purgedCard.name : "");
        }
    }
}