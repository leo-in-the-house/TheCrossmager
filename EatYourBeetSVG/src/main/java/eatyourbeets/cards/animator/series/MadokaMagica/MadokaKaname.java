package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.special.Curse_GriefSeed;
import eatyourbeets.cards.animator.special.MadokaKaname_KriemhildGretchen;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MadokaKaname extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MadokaKaname.class)
            .SetSkill(4, CardRarity.RARE, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.MadokaMagica_Witch(MadokaKaname_KriemhildGretchen.DATA));
                data.AddPreview(new MadokaKaname_KriemhildGretchen(), true);
                data.AddPreview(new Curse_GriefSeed(), false);
            });

    public MadokaKaname()
    {
        super(DATA);

        Initialize(0, 0, 2, 2);

        SetAffinity_White(2);
        SetAffinity_Pink(2);

        SetExhaust(true);
        SetDelayed(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetDelayed(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainIntangible(secondaryValue);

        if (!upgraded)
        {
            GameActions.Bottom.ExhaustFromPile(name, 999, p.drawPile, p.hand, p.discardPile)
                .ShowEffect(true, true)
                .SetOptions(true, true)
                .SetFilter(c -> c.type == CardType.CURSE);
        }
        else {
            GameActions.Bottom.ExhaustFromPile(name, 999, p.drawPile, p.hand, p.discardPile)
                .ShowEffect(true, true)
                .SetOptions(true, true)
                .SetFilter(GameUtilities::IsHindrance);
        }
    }
}
