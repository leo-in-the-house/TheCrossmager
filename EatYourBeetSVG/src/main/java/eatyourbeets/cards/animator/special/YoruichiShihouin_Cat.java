package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Bleach.YoruichiShihouin;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YoruichiShihouin_Cat extends AnimatorCard {
    public static final EYBCardData DATA = Register(YoruichiShihouin_Cat.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.Bleach)
            .PostInitialize(data -> data.AddPreview(new YoruichiShihouin(), false));


    public YoruichiShihouin_Cat() {
        super(DATA);

        Initialize(0, 0, 6);
        SetUpgrade(0, 0, 4);

        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainTemporaryHP(magicNumber);
        GameActions.Bottom.ChangeStance(CalmStance.STANCE_ID);
        GameActions.Last.ReplaceCard(uuid, new YoruichiShihouin())
                .SetUpgrade(upgraded);
    }
}