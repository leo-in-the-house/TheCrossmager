package eatyourbeets.cards.animatorClassic.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;

public class Magilou_Bienfu extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Magilou_Bienfu.class).SetSkill(-2, CardRarity.SPECIAL, EYBCardTarget.None).SetColor(CardColor.COLORLESS);

    public Magilou_Bienfu()
    {
        super(DATA);

        Initialize(0, 0);

        SetEthereal(true);
        this.series = CardSeries.TalesOfBerseria;
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }

    @Override
    public void triggerWhenDrawn()
    {
        GameActions.Bottom.GainBlue(1, true);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        GameActions.Last.MoveCard(this, player.drawPile).ShowEffect(true, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        this.cantUseMessage = UNPLAYABLE_MESSAGE;
        return false;
    }
}