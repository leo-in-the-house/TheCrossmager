package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Atelier.LydieMalen;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SuelleMalen extends AnimatorCard {
    public static final EYBCardData DATA = Register(SuelleMalen.class)
            .SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Ranged, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new LydieMalen(), false);
    }

    public SuelleMalen() {
        super(DATA);

        Initialize(9, 0, 2);
        SetUpgrade(4, 0, 0);

        SetAffinity_Red(2, 0, 2);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.GUNSHOT);
        }

        GameActions.Bottom.MakeCardInDrawPile(new LydieMalen());

        GameActions.Last.Exhaust(this);
    }
}