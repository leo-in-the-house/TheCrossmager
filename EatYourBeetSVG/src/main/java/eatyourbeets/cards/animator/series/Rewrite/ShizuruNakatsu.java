package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ShizuruNakatsu extends AnimatorCard {
    public static final EYBCardData DATA = Register(ShizuruNakatsu.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public ShizuruNakatsu() {
        super(DATA);

        Initialize(0, 4, 2);
        SetUpgrade(0, 4, 0);

        SetAffinity_Green(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.DiscardFromHand(name, magicNumber, true)
            .SetOptions(false, false, false)
            .SetFilter(card -> card.type == CardType.SKILL)
            .AddCallback(cards -> {
               if (cards.size() >= magicNumber) {
                   GameActions.Top.ChangeStance(TranceStance.STANCE_ID);
               }
            });
    }
}