package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.GirlDeMo;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Yui extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Yui.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new GirlDeMo(), true);
    }

    public Yui()
    {
        super(DATA);

        Initialize(0, 0, 1, 2);

        SetAffinity_Light(2);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Motivate(secondaryValue);

       GameActions.Bottom.MakeCardInDrawPile(new GirlDeMo())
               .SetUpgrade(upgraded, true);
    }
}