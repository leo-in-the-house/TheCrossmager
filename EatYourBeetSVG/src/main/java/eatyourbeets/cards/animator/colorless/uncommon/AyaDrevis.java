package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.AyaDrevis_Doll;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AyaDrevis extends AnimatorCard {
    public static final EYBCardData DATA = Register(AyaDrevis.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.MadFather)
            .PostInitialize(data -> data.AddPreview(new AyaDrevis_Doll(), true));

    public AyaDrevis() {
        super(DATA);

        Initialize(5, 5, 0);
        SetUpgrade(0, 2, 0);

        SetAffinity_Brown(1, 0, 1);
        SetAffinity_Teal(1, 0, 1);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.MakeCardInHand(new AyaDrevis_Doll())
                .SetUpgrade(upgraded, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.SMASH);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainBrown(1);
        GameActions.Bottom.GainTeal(1);
    }
}