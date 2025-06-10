package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.ThrowingKnife;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shinku extends AnimatorCard {
    public static final EYBCardData DATA = Register(Shinku.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Shinku() {
        super(DATA);

        Initialize(0, 0, 3, 3);
        SetUpgrade(0, 0, 2, 2);

        SetAffinity_Red(1);
        SetAffinity_Violet(1);
    }

    @Override
    public void onRetained()
    {
        super.onRetained();

        GameUtilities.IncreaseMagicNumber(this, magicNumber, true);
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.PlayCard(ThrowingKnife.GetRandomCardInBattle(), GameUtilities.GetRandomEnemy(true))
                    .SetDuration(Settings.ACTION_DUR_XFAST, true);
        }

        magicNumber = baseMagicNumber;
    }
}