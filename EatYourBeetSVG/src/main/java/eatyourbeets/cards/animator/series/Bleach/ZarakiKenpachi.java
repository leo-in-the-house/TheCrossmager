package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ZarakiKenpachi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ZarakiKenpachi.class)
            .SetPower(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public ZarakiKenpachi()
    {
        super(DATA);

        Initialize(0, 3, 2);
        SetUpgrade(0, 6, 0);


        SetDelayed(true);
        SetEthereal(true);

        SetAffinity_Red(2, 0, 1);
        SetAffinity_Black(1, 0, 0);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);

        int strengthToGain = 0;

        for (AbstractCard card : player.masterDeck.group) {
            if (GameUtilities.HasRedAffinity(card)) {
                strengthToGain++;
            }
        }

        if (strengthToGain > 0) {
            GameActions.Bottom.GainStrength(strengthToGain);
        }
    }

}