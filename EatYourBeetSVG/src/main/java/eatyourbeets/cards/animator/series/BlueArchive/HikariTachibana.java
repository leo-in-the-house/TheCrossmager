package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.HikariTachibana_Nozomi;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class HikariTachibana extends AnimatorCard {
    public static final EYBCardData DATA = Register(HikariTachibana.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new HikariTachibana_Nozomi(), true));


    public HikariTachibana() {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 3);

        SetExhaust(true);

        SetAffinity_Yellow(1);
        SetAffinity_Green(1);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();

        GameActions.Bottom.MakeCardInDrawPile(new HikariTachibana_Nozomi());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainTemporaryHP(magicNumber);
    }
}