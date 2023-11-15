package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class NoriSakurada extends AnimatorCard {
    public static final EYBCardData DATA = Register(NoriSakurada.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    private final int BASE_TEMP_HP = 4;

    public NoriSakurada() {
        super(DATA);

        Initialize(0, 0, BASE_TEMP_HP, 1);
        SetUpgrade(0, 0, 0, 1);

        SetExhaust(true);
        SetDelayed(true);

        SetAffinity_White(1);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetExhaust(false);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void onRetained()
    {
        super.onRetained();

        GameUtilities.IncreaseMagicNumber(this, secondaryValue, false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainTemporaryHP(magicNumber);
        GameActions.Bottom.Callback(() -> {
            GameUtilities.DecreaseMagicNumber(this, magicNumber - BASE_TEMP_HP, false);
        });
    }
}