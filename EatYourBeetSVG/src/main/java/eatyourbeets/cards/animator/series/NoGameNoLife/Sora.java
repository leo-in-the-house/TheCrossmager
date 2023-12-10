package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Sora_BattlePlan1;
import eatyourbeets.cards.animator.special.Sora_BattlePlan2;
import eatyourbeets.cards.animator.special.Sora_BattlePlan3;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Sora extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Sora.class)
            .SetSkill(4, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Sora_BattlePlan1(), true);
                data.AddPreview(new Sora_BattlePlan2(), true);
                data.AddPreview(new Sora_BattlePlan3(), true);
            });

    public Sora()
    {
        super(DATA);

        Initialize(0, 4, 2);
        SetUpgrade(0, 4, 0);

        SetAffinity_Pink(2, 0, 2);
        SetAffinity_Yellow(2, 0, 2);

        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        super.triggerOnOtherCardPlayed(c);

        GameActions.Bottom.Callback(this::RefreshCost);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        RefreshCost();
    }

    public void RefreshCost()
    {
        CostModifiers.For(this).Set(-1 * JUtils.Count(player.hand.group, card -> (card.costForTurn == 0)));
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.group.add(new Sora_BattlePlan1());
        group.group.add(new Sora_BattlePlan2());
        group.group.add(new Sora_BattlePlan3());
        GameActions.Bottom.SelectFromPile(name, 1, group)
        .SetOptions(false, false)
        .AddCallback(cards ->
        {
            for (AbstractCard card : cards)
            {
                GameActions.Bottom.MakeCardInHand(card);
            }
        });
    }
}