package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.DevaPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Nahida extends AnimatorCard {
    public static final EYBCardData DATA = Register(Nahida.class)
            .SetPower(0, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Nahida() {
        super(DATA);

        Initialize(0, 0, 4);
        SetUpgrade(0, 0, -1);

        SetAffinity_Green(2);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetEthereal(false);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        if (super.cardPlayable(m)) {
            return GameUtilities.GetUniqueOrbsCount() >= magicNumber;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new DevaPower(p));
    }
}