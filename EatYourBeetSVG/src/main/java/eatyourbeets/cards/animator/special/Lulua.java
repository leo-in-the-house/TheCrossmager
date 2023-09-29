package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Lulua extends AnimatorCard {
    public static final EYBCardData DATA = Register(Lulua.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Elemental)
            .SetSeries(CardSeries.Atelier);

    public Lulua() {
        super(DATA);

        Initialize(6, 0, 2);
        SetUpgrade(3, 0, 0);

        SetAffinity_Pink(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.ICE);

        GameActions.Bottom.GainPink(magicNumber, upgraded);

        GameActions.Bottom.SelectFromHand(name, 1, false)
        .SetOptions(false, false, false)
        .SetMessage(GR.Common.Strings.HandSelection.Copy)
        .AddCallback(cards ->
        {
            for (AbstractCard c : cards)
            {
                GameActions.Top.MakeCardInDiscardPile(c.makeStatEquivalentCopy());
            }
            GameActions.Top.MakeCardInDiscardPile(this.makeStatEquivalentCopy());
        });

    }
}