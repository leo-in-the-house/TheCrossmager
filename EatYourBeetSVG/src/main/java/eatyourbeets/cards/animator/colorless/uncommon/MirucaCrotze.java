package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.DamageModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MirucaCrotze extends AnimatorCard {
    public static final EYBCardData DATA = Register(MirucaCrotze.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Atelier);

    public MirucaCrotze() {
        super(DATA);

        Initialize(7, 0, 5);
        SetUpgrade(3, 0, 5);

        SetAffinity_Brown(1, 0, 1);
        SetAffinity_Yellow(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.SPEAR);

        if (IsStarter())
        {
            for (AbstractCard card : player.hand.group) {
                if (card.type == CardType.ATTACK) {
                    DamageModifiers.For(card).Add(magicNumber);
                }
            }
        }
    }
}