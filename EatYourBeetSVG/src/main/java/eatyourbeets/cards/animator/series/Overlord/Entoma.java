package eatyourbeets.cards.animator.series.Overlord;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Entoma extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Entoma.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Piercing)
            .SetSeriesFromClassPackage();

    public Entoma()
    {
        super(DATA);

        Initialize(8, 0, 2);
        SetUpgrade(3, 0, 1);

        SetAffinity_Violet(1);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.POISON)
        .SetDamageEffect(e -> GameEffects.List.Add(VFX.Bite(e.hb, Color.GREEN)).duration);
        GameActions.Bottom.ApplyPoison(p, m, magicNumber);
    }
}