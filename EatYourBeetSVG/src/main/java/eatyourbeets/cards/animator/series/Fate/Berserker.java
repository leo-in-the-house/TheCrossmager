package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Berserker extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Berserker.class)
            .SetAttack(3, CardRarity.COMMON)
            .SetSeriesFromClassPackage();

    public Berserker()
    {
        super(DATA);

        Initialize(20, 0, 14);
        SetUpgrade(6, 0, 0);

        SetAffinity_Red(2);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(VFX.VerticalImpact(m.hb));
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY)
        .AddCallback(m.currentBlock, (initialBlock, target) ->
        {
            if (GameUtilities.IsDeadOrEscaped(target) || (initialBlock > 0 && target.currentBlock <= 0))
            {
                GameActions.Bottom.GainBlock(this.magicNumber);
            }
        });
        GameActions.Bottom.ShakeScreen(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.MED);
    }
}