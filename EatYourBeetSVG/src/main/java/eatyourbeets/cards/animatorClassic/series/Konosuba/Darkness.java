package eatyourbeets.cards.animatorClassic.series.Konosuba;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import eatyourbeets.cards.animatorClassic.special.Darkness_Adrenaline;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.animatorClassic.DarknessPower;
import eatyourbeets.utilities.GameActions;

public class Darkness extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Darkness.class).SetSeriesFromClassPackage().SetPower(1, CardRarity.UNCOMMON);
    static
    {
        DATA.AddPreview(new Darkness_Adrenaline(), false);
    }

    public Darkness()
    {
        super(DATA);

        Initialize(0, 2, 2);
        SetUpgrade(0, 1, 1);

        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new PlatedArmorPower(p, magicNumber));
        GameActions.Bottom.StackPower(new DarknessPower(p, 1));
    }
}