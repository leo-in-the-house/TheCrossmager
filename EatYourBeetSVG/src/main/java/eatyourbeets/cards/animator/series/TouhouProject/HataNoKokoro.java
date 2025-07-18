package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.basic.MoveCard;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.JUtils;

public class HataNoKokoro extends AnimatorCard
{
    public static final EYBCardData DATA = Register(HataNoKokoro.class)
            .SetSkill(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    protected boolean hasPlayableCards = false;

    public HataNoKokoro()
    {
        super(DATA);

        Initialize(0, 2, 3);
        SetUpgrade(0, 0, 3);

        SetEthereal(true);
        SetExhaust(true);

        SetAffinity_Red(1);
        SetAffinity_Green(1);
        SetAffinity_Blue(1);

        SetCardPreview(this::CanSelectCard)
        .SetGroupType(CardGroup.CardGroupType.EXHAUST_PILE);
    }

    @Override
    protected void OnUpgrade()
    {
        SetExhaust(false);
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        hasPlayableCards = false;
        for (AbstractCard c : player.exhaustPile.group)
        {
            if (CanSelectCard(c))
            {
                hasPlayableCards = true;
                break;
            }
        }
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

        GameActions.Bottom.PurgeFromPile(name, 1, p.exhaustPile)
        //.SetOptions(false, false)
        .SetFilter(this::CanSelectCard)
        .AddCallback(m, (target, cards) ->
        {
            for (AbstractCard c : cards)
            {
                GameActions.Bottom.ApplyPower(player, new HataNoKokoroPower(player, target, c));
            }
        });
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return hasPlayableCards;
    }

    protected boolean CanSelectCard(AbstractCard card)
    {
        return !AbstractDungeon.actionManager.cardsPlayedThisCombat.contains(card) && (card.cost > UNPLAYABLE_COST);
    }

    public static class HataNoKokoroPower extends AnimatorPower
    {
        private static int counter;
        private final AbstractCard card;
        private final AbstractMonster target;

        public HataNoKokoroPower(AbstractCreature owner, AbstractMonster target, AbstractCard card)
        {
            super(owner, HataNoKokoro.DATA);

            this.ID += (counter++);
            this.card = card;
            this.target = target;

            Initialize(-1);
        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(0, JUtils.ModifyString(card.name, w -> "#y" + w));
        }

        @Override
        public void atStartOfTurnPostDraw()
        {
            super.atStartOfTurnPostDraw();

            card.target_x = MoveCard.DEFAULT_CARD_X_LEFT;
            card.target_y = MoveCard.DEFAULT_CARD_Y;
            GameActions.Last.PlayCopy(card, target);
            GameActions.Last.PlayCard(card, CombatStats.PurgedCards, target)
                    .SetPurge(true);
            RemovePower();
        }
    }
}