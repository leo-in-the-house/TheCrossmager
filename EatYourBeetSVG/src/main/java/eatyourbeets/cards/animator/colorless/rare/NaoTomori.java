package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.common.Curse_Normality;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class NaoTomori extends AnimatorCard {
    public static final EYBCardData DATA = Register(NaoTomori.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Charlotte);

    public NaoTomori() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Pink(1);
        SetAffinity_Green(1);

        SetRetain(true);
        SetLoyal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        if (info.TryActivateStarter())
        {
            GameActions.Bottom.SelectFromHand(name, 1, false)
                .SetOptions(true, true, true)
                .SetFilter(card -> card.type == CardType.POWER || card.rarity == CardRarity.RARE)
                .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
                .AddCallback(cards ->
                {
                    if (cards.size() > 0) {
                        GameActions.Top.ReplaceCard(cards.get(0).uuid, new Curse_Normality());
                        GameActions.Top.GainIntangible(1);
                    }
                });
        }
    }
}