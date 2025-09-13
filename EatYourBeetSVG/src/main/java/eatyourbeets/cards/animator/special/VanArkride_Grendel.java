package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class VanArkride_Grendel extends AnimatorCard {
    public static final EYBCardData DATA = Register(VanArkride_Grendel.class)
            .SetAttack(0, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public VanArkride_Grendel() {
        super(DATA);

        Initialize(20, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Blue(2, 0, 2);
        SetAffinity_Red(1, 0, 1);
        SetAffinity_Green(1, 0, 1);

        SetFading(true);
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
            if (i != magicNumber-1) {
                GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.PUNCH)
                        .SetVFXColor(Color.NAVY);
            }
            else {
                GameActions.Bottom.Wait(0.7f);
                GameActions.Bottom.VFX(VFX.VerticalImpact(m.hb));
                GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY)
                        .SetVFXColor(Color.NAVY);
                GameActions.Bottom.ShakeScreen(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.MED);
            }
        }
    }
}