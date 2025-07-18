package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import eatyourbeets.cards.animator.series.NoGameNoLife.ChlammyZell;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.animator.ChlammyZellPower;
import eatyourbeets.utilities.GameActions;

public class ChlammyZell_Scheme extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ChlammyZell_Scheme.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(ChlammyZell.DATA.Series);

    public ChlammyZell_Scheme()
    {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 1);

        SetAffinity_Pink(1);
        SetAffinity_Violet(1);

        SetRetain(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(new FlickCoinEffect(p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY + p.hb.height), 0.15f);
        GameActions.Bottom.StackPower(new ChlammyZellPower(p, magicNumber));
    }
}