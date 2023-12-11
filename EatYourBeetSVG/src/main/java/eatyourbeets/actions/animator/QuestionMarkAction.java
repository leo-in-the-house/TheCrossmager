package eatyourbeets.actions.animator;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.core.Settings;
import eatyourbeets.actions.EYBAction;
import eatyourbeets.cards.animator.colorless.uncommon.AiKizuna;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.EYBCardAffinity;

import java.util.ArrayList;

public class QuestionMarkAction extends EYBAction
{
    private static ArrayList<AnimatorCard> cardPool;
    private final AiKizuna aiKizuna;

    public QuestionMarkAction(AiKizuna instance)
    {
        super(ActionType.CARD_MANIPULATION, Settings.ACTION_DUR_XFAST);

        this.aiKizuna = instance;

        Initialize(1);
    }

    @Override
    protected void FirstUpdate()
    {
        final AnimatorCard copy = aiKizuna.copy = GetRandomCard();
        final int index = player.hand.group.indexOf(aiKizuna);
        if (copy != null && index >= 0)
        {
            for (EYBCardAffinity a : copy.affinities.List)
            {
                a.level = 0;
            }

            copy.affinities.Set(Affinity.Star, aiKizuna.upgraded ? 2 : 1);
            copy.triggerWhenCreated(false);

            if (aiKizuna.upgraded)
            {
                copy.upgrade();
            }

            copy.current_x = aiKizuna.current_x;
            copy.current_y = aiKizuna.current_y;
            copy.target_x = aiKizuna.target_x;
            copy.target_y = aiKizuna.target_y;
            copy.uuid = aiKizuna.uuid;

            player.hand.group.remove(index);
            player.hand.group.add(index, copy);
            player.hand.glowCheck();

            //CombatStats.onStartOfTurn.Subscribe(questionMark);
        }

        Complete();
    }

    private static AnimatorCard GetRandomCard()
    {
        if (cardPool == null)
        {
            cardPool = new ArrayList<>();

            for (AbstractCard c : GameUtilities.GetAvailableCards())
            {
                if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS)
                {
                    if (c instanceof AnimatorCard
                    && !(c instanceof AiKizuna)
                    && GameUtilities.IsObtainableInCombat(c)
                    && c.rarity != AbstractCard.CardRarity.BASIC)
                    {
                        cardPool.add((AnimatorCard)c);
                    }
                }
            }
        }

        return (AnimatorCard) GameUtilities.GetRandomElement(cardPool).makeCopy();
    }
}