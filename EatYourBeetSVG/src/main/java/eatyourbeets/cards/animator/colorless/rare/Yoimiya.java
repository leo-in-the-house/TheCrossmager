package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.ThrowingKnife;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnCardCreatedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Yoimiya extends AnimatorCard {
    public static final EYBCardData DATA = Register(Yoimiya.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.Random)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public Yoimiya() {
        super(DATA);

        Initialize(4, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.FIRE);

        GameActions.Bottom.StackPower(new YoimiyaPower(p, 1));

        GameActions.Bottom.CreateThrowingKnives(player.filledOrbCount());
    }

    public static class YoimiyaPower extends AnimatorPower implements OnCardCreatedSubscriber {
        public YoimiyaPower(AbstractCreature owner, int amount) {
            super(owner, Yoimiya.DATA);
            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onCardCreated.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onCardCreated.Unsubscribe(this);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void OnCardCreated(AbstractCard card, boolean startOfBattle) {
            if (card instanceof AnimatorCard && ThrowingKnife.DATA.IsCard(card)) {
                AnimatorCard animatorCard = (AnimatorCard) card;
                animatorCard.SetAttackTarget(EYBCardTarget.ALL);
            }
        }
    }
}