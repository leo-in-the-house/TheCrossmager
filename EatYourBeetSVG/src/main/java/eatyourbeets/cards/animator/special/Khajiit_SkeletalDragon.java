package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Khajiit_SkeletalDragon extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Khajiit_SkeletalDragon.class)
            .SetAttack(2, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Overlord);

    private AbstractCard summoner;

    public Khajiit_SkeletalDragon()
    {
        this(null);
    }

    public Khajiit_SkeletalDragon(AbstractCard summoner)
    {
        super(DATA);

        Initialize(16, 12);
        SetUpgrade(4, 4);

        SetAffinity_Black(2, 0, 2);
        SetAffinity_Violet(2, 0, 2);

        this.summoner = summoner;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.DARK)
        .SetSoundPitch(0.4f, 0.5f)
        .SetDamageEffect(c -> GameEffects.List.Add(VFX.Claw(c.hb, Color.VIOLET, Color.BLACK).SetScale(2f)).duration * 0.6f);
        GameActions.Bottom.ShakeScreen(0.4f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.MED);
    }
}