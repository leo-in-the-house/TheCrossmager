package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.colorless.uncommon.Magilou;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Magilou_Bienfu extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Magilou_Bienfu.class)
            .SetStatus(UNPLAYABLE_COST, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(Magilou.DATA.Series);

    public Magilou_Bienfu()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Blue(1);
        SetHaste(true);
    }

    @Override
    public void triggerWhenDrawn()
    {
        GameActions.Bottom.GainBlue(magicNumber, true);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        GameActions.Last.MoveCard(this, player.drawPile).ShowEffect(true, true);
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        return false;
    }
}