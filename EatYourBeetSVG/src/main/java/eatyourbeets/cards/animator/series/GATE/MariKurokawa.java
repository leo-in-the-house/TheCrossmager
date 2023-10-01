package eatyourbeets.cards.animator.series.GATE;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MariKurokawa extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MariKurokawa.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)

            .SetSeriesFromClassPackage();
    public static final int DISCARD_AMOUNT = 2;

    public MariKurokawa()
    {
        super(DATA);

        Initialize(0, 0, 5, 0);
        SetUpgrade(0, 0, 2, 0);

        SetAffinity_White(1, 0, 0);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (AbstractCard card : player.hand.group) {
            if (card.type == CardType.ATTACK) {
                GameActions.Bottom.Discard(card, player.hand)
                .ShowEffect(true, true)
                .AddCallback(c -> {
                    GameActions.Top.GainTemporaryHP(magicNumber);
                });
            }
        }
    }
}