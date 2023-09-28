package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.interfaces.subscribers.OnCardCreatedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KlaudiaValentz extends AnimatorCard {
    public static final EYBCardData DATA = Register(KlaudiaValentz.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public KlaudiaValentz() {
        super(DATA);

        Initialize(0, 5, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Yellow(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new KlaudiaValentzPower(p, 1));
    }

    public static class KlaudiaValentzPower extends AnimatorPower implements OnCardCreatedSubscriber
    {
        public KlaudiaValentzPower(AbstractCreature owner, int amount)
        {
            super(owner, KlaudiaValentz.DATA);

            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onCardCreated.Subscribe(this);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            SetEnabled(false);
            RemovePower();
            flash();
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onCardCreated.Unsubscribe(this);
        }

        @Override
        public void OnCardCreated(AbstractCard card, boolean startOfBattle) {
            if (enabled && !startOfBattle) {
                GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);

                reducePower(1);
                flashWithoutSound();

                if (amount <= 0) {
                    SetEnabled(false);
                    RemovePower();
                    flash();
                }
            }
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}