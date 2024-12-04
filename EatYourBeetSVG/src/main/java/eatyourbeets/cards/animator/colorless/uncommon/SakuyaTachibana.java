package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SakuyaTachibana extends AnimatorCard {
    public static final EYBCardData DATA = Register(SakuyaTachibana.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.Normal)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GodEater);

    public SakuyaTachibana() {
        super(DATA);

        Initialize(11, 0, 0);
        SetUpgrade(8, 0, 0);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Pink(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT).AddCallback(e ->
        {
            int timesMotivate = e.lastDamageTaken / 10;

            if (timesMotivate > 0) {
                GameActions.Top.Motivate(timesMotivate);
            }
        });
    }
}