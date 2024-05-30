package eatyourbeets.relics.animator;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import eatyourbeets.cards.animator.colorless.uncommon.AiKizuna;
import eatyourbeets.relics.AnimatorRelic;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.FieldInfo;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GenericCondition;
import eatyourbeets.utilities.JUtils;

import java.util.ArrayList;

public class RollingCubes extends AnimatorRelic
{
    private static final FieldInfo<Boolean> _isBoss = JUtils.GetField("isBoss", RewardItem.class);
    private static final CardGroup tempGroupCommon = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    private static final CardGroup tempGroupUncommon = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    private static final CardGroup tempGroupRare = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    private static final RewardItem fakeRewardItem = new RewardItem(0, true);
    static
    {
        fakeRewardItem.type = RewardItem.RewardType.CARD;
        fakeRewardItem.cards = new ArrayList<>();
    }

    public static final String ID = CreateFullID(RollingCubes.class);
    public static final int MAX_STORED_USES = 3;
    public static final int USES_PER_ELITE = 1;

    private int lastRerollFloor = -1;

    public RollingCubes()
    {
        super(ID, RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription()
    {
        return FormatDescription(0, USES_PER_ELITE, MAX_STORED_USES);
    }

    @Override
    public void onEquip()
    {
        super.onEquip();

        SetCounter(0);
    }

    @Override
    public void onEnterRoom(AbstractRoom room)
    {
        super.onEnterRoom(room);

        if (room instanceof MonsterRoomElite || room instanceof MonsterRoomBoss)
        {
            SetCounter(Math.min(MAX_STORED_USES, counter + USES_PER_ELITE));
            flash();
        }
    }

    public boolean CanActivate(RewardItem rewardItem)
    {
        return CanReroll() && !GameUtilities.InBattle() && rewardItem != null && rewardItem.type == RewardItem.RewardType.CARD
                && !_isBoss.Get(rewardItem) && !JUtils.Any(rewardItem.cards, c -> c.color == AbstractCard.CardColor.COLORLESS);
    }

    public boolean CanReroll()
    {
        return counter > 0 && AbstractDungeon.floorNum != lastRerollFloor;
    }

    public ArrayList<AbstractCard> Reroll(RewardItem rewardItem)
    {
        SetCounter(counter - 1);
        lastRerollFloor = AbstractDungeon.floorNum;

        fakeRewardItem.type = RewardItem.RewardType.CARD;
        fakeRewardItem.cards.clear();
        fakeRewardItem.cards.addAll(rewardItem.cards);

        final ArrayList<AbstractCard> pool = GameUtilities.GetAvailableCards(GenericCondition.FromT2((a, c) -> GameUtilities.IsSeries(c, GR.Animator.Data.SelectedLoadout.Series), null));
        final ArrayList<AbstractCard> replacement = new ArrayList<>();
        for (AbstractCard c : rewardItem.cards)
        {
            final AbstractCard card = RerollCard(fakeRewardItem, c, pool);
            if (card != null)
            {
                fakeRewardItem.cards.add(card);
                replacement.add(card);
                pool.remove(card);
            }
            else
            {
                replacement.add(new AiKizuna());
            }
        }

        boolean upgraded = false;
        for (AbstractCard c : replacement) {
            if (AbstractDungeon.actNum >= 3) {
                c.upgrade();
            }
            else if (AbstractDungeon.actNum == 2 && !upgraded) {
                c.upgrade();
                upgraded = true;
            }
        }

        return replacement;
    }

    private AbstractCard RerollCard(RewardItem rewardItem, AbstractCard card, ArrayList<AbstractCard> availableCards)
    {
        if (availableCards.size() > 0)
        {
            final CardGroup g1 = AbstractDungeon.srcCommonCardPool;
            final CardGroup g2 = AbstractDungeon.srcUncommonCardPool;
            final CardGroup g3 = AbstractDungeon.srcRareCardPool;

            tempGroupCommon.clear();
            tempGroupUncommon.clear();
            tempGroupRare.clear();

            for (AbstractCard sc : availableCards)
            {
                if (g1.contains(sc))
                {
                    tempGroupCommon.addToTop(sc);
                }
                else if (g2.contains(sc))
                {
                    tempGroupUncommon.addToTop(sc);
                }
                else if (g3.contains(sc)) {
                    tempGroupRare.addToTop(sc);
                }
            }

            AbstractDungeon.srcCommonCardPool = tempGroupCommon;
            AbstractDungeon.srcUncommonCardPool = tempGroupUncommon;
            AbstractDungeon.srcRareCardPool = tempGroupRare;

            final AbstractCard reward = GR.Common.Dungeon.GetRandomRewardCardRollingCubes(rewardItem.cards, true,true);

            AbstractDungeon.srcCommonCardPool = g1;
            AbstractDungeon.srcUncommonCardPool = g2;
            AbstractDungeon.srcRareCardPool = g3;

            return reward;
        }

        return GR.Common.Dungeon.GetRandomRewardCardRollingCubes(rewardItem.cards, false,true);
    }
}