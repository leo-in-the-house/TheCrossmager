package eatyourbeets.cards.animatorClassic.special;

import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;

public class Yuuichirou_Asuramaru extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Yuuichirou_Asuramaru.class).SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None);

    public Yuuichirou_Asuramaru()
    {
        super(DATA);

        Initialize(0, 0, 3, 2);

        SetExhaust(true);
        this.series = CardSeries.OwariNoSeraph;
    }

    @Override
    protected void OnUpgrade()
    {
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new DemonFormPower(p, secondaryValue));
        GameActions.Bottom.GainBlue(magicNumber);
        GameActions.Bottom.GainGreen(magicNumber);
        GameActions.Bottom.GainRed(magicNumber);
        GameActions.Bottom.MakeCardInHand(new Wound());
        GameActions.Bottom.MakeCardInHand(new Wound());
    }
}