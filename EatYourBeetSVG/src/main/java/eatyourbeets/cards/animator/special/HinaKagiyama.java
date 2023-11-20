package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnAfterCardDrawnSubscriber;
import eatyourbeets.interfaces.subscribers.OnCardCreatedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

public class HinaKagiyama extends AnimatorCard
{
    public static final EYBCardData DATA = Register(HinaKagiyama.class)
            .SetPower(1, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TouhouProject)
            
            .PostInitialize(data -> data.AddPreview(new Special_Miracle(), false));

    public HinaKagiyama()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetCostUpgrade(-1);

        SetAffinity_Violet(1);
        SetAffinity_White(1);

        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new HinaKagiyamaPower(p, magicNumber));
    }

    public static class HinaKagiyamaPower extends AnimatorPower implements OnAfterCardDrawnSubscriber, OnCardCreatedSubscriber
    {
        public HinaKagiyamaPower(AbstractCreature owner, int amount)
        {
            super(owner, HinaKagiyama.DATA);

            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAfterCardDrawn.Subscribe(this);
            CombatStats.onCardCreated.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAfterCardDrawn.Unsubscribe(this);
            CombatStats.onCardCreated.Unsubscribe(this);
        }

        @Override
        public void OnAfterCardDrawn(AbstractCard card)
        {
            Activate(card);
        }

        @Override
        public void OnCardCreated(AbstractCard card, boolean startOfBattle)
        {
            Activate(card);
        }

        public void Activate(AbstractCard c)
        {
            if (c.type == AbstractCard.CardType.CURSE)
            {
                GameActions.Bottom.Draw(1);
                flashWithoutSound();
            }
        }
    }
}