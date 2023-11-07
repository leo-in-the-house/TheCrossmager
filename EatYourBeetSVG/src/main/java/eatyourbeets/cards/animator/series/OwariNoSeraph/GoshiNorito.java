package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.vfx.megacritCopy.SmokeBombEffect2;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class GoshiNorito extends AnimatorCard
{
    public static final EYBCardData DATA = Register(GoshiNorito.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage();

    public GoshiNorito()
    {
        super(DATA);

        Initialize(0,0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(1);
        SetAffinity_Pink(1);

        SetFading(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        final float stepX = Settings.WIDTH / 8f; // 0 (1 2 3 4 5 6 7) 8
        GameActions.Bottom.VFX(new SmokeBombEffect2(stepX * 4, p.hb.cY), 0.02f);
        for (int i = 1; i <= 3; i++)
        {
            final float y = p.hb.cY + (p.hb.height * ((i % 2 == 0) ? +0.25f : -0.25f));
            GameActions.Bottom.VFX(new SmokeBombEffect2(stepX * (4 - i), y), 0.02f);
            GameActions.Bottom.VFX(new SmokeBombEffect2(stepX * (4 + i), y), 0.02f);
        }

        for (AbstractCard card : player.hand.group) {
            GameActions.Bottom.Exhaust(card)
                 .AddCallback(c -> {
                    if (c != null && !GameUtilities.IsHindrance(c)) {
                        GameActions.Top.MakeCardInHand(GameUtilities.Imitate(c));
                    }
                 });
        }

        GameActions.Bottom.Draw(1);
    }
}