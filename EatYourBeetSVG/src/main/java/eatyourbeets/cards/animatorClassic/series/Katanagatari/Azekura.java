package eatyourbeets.cards.animatorClassic.series.Katanagatari;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Azekura extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Azekura.class).SetSeriesFromClassPackage().SetSkill(2, CardRarity.COMMON, EYBCardTarget.None);

    public Azekura()
    {
        super(DATA);

        Initialize(0, 11, 3, 2);
        SetUpgrade(0, 0, -1, 0);

        
        
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Bottom.GainThorns(secondaryValue);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainPlatedArmor(secondaryValue);

        for (AbstractCard c : GameUtilities.GetOtherCardsInHand(this))
        {
            if (c.baseBlock > 0)
            {
                GameUtilities.DecreaseBlock(c, magicNumber, false);
                GameUtilities.Flash(c, Color.RED, false);
            }
        }

        if (WrathStance.IsActive())
        {
            GameActions.Bottom.GainThorns(secondaryValue);
        }
    }
}