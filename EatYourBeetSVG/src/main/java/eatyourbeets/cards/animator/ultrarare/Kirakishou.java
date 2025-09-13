package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.LinkedList;
import java.util.List;

public class Kirakishou extends AnimatorCard_UltraRare {
    public static final EYBCardData DATA = Register(Kirakishou.class)
            .SetPower(1, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.RozenMaiden);

    public Kirakishou() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Green(2);
        SetAffinity_Pink(2);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new KirakishouPower(p, magicNumber));
    }

    public static class KirakishouPower extends AnimatorClickablePower {

        private static List<AbstractCard> purgedCards = new LinkedList<>();

        public KirakishouPower(AbstractCreature owner, int amount) {
            super(owner, Kirakishou.DATA, PowerTriggerConditionType.Special, 0, Kirakishou.KirakishouPower::CheckCondition, __ -> {});

            triggerCondition.SetUses(3, true, true);

            Initialize(amount);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            purgedCards = new LinkedList<>();
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            purgedCards.clear();
        }

        @Override
        public void atStartOfTurn() {
            super.atStartOfTurn();

            GameActions.Bottom.MoveCards(player.drawPile, CombatStats.PurgedCards, amount)
                .ShowEffect(true, true)
                .SetOrigin(CardSelection.Top)
                .AddCallback(purgedCards::addAll);
        }

        private static boolean CheckCondition(int cost)
        {
            return !purgedCards.isEmpty();
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            playApplyPowerSfx();

            if (purgedCards.size() > 0) {
                CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                for (AbstractCard card : purgedCards) {
                    AbstractCard curCard = card.makeCopy();

                    if (card.upgraded) {
                        curCard.upgrade();
                    }

                    group.addToBottom(curCard);
                }

                GameActions.Bottom.SelectFromPile(name, 1, group)
                    .SetMessage(GR.Common.Strings.HandSelection.Obtain)
                    .SetOptions(false, false)
                    .AddCallback(cards -> {
                        for (AbstractCard card : cards) {
                            GameUtilities.Retain(card);
                            CostModifiers.For(card).Add(-1);
                            GameActions.Top.MakeCardInHand(card)
                                    .SetUpgrade(card.upgraded, true);
                        }
                    });
            }
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, amount);
        }
    }
}