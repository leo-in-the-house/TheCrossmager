package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Dori extends AnimatorCard {
    public static final EYBCardData DATA = Register(Dori.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Dori() {
        super(DATA);

        Initialize(0, 0, 10);
        SetUpgrade(0, 0, 5);

        SetAffinity_Yellow(1);

        SetExhaust(true);
        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainGold(magicNumber);
    }
}