package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.animator.special.YoruichiShihouin_Cat;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YoruichiShihouin extends AnimatorCard {
    public static final EYBCardData DATA = Register(YoruichiShihouin.class)
            .SetSkill(3, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new YoruichiShihouin_Cat(), false));

    public YoruichiShihouin() {
        super(DATA);

        Initialize(0, 18, 2);
        SetUpgrade(0, 12, 0);

        SetAffinity_Yellow(2, 0, 2);
        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChannelOrbs(Lightning::new, magicNumber);

        if (GameUtilities.InStance(WrathStance.STANCE_ID)) {
            GameActions.Last.ReplaceCard(uuid, new YoruichiShihouin_Cat())
                    .SetUpgrade(upgraded);
        }
    }
}