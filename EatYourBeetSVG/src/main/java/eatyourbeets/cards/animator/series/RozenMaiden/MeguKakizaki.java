package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MeguKakizaki extends AnimatorCard {
    public static final EYBCardData DATA = Register(MeguKakizaki.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public MeguKakizaki() {
        super(DATA);

        Initialize(0, 0, 6, 3);
        SetCostUpgrade(-1);

        SetAffinity_Black(1);

        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.FetchFromPile(name, 1, player.discardPile, player.drawPile, player.exhaustPile)
            .SetFilter(card -> GameUtilities.HasAffinity(card, Affinity.Black))
            .SetOptions(false, true, false)
            .AddCallback(cards -> {
                if (cards.size() > 0) {
                    for (AbstractCard card : cards) {
                        GameUtilities.Retain(card);
                    }
                    GameActions.Top.TakeDamageAtEndOfTurn(magicNumber);
                }
            });
    }
}