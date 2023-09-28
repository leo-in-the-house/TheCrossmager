package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Valeria extends AnimatorCard {
    public static final EYBCardData DATA = Register(Valeria.class)
            .SetAttack(1, CardRarity.UNCOMMON)
            .SetColor(CardColor.COLORLESS)
            .SetSeriesFromClassPackage();

    public Valeria() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }
}