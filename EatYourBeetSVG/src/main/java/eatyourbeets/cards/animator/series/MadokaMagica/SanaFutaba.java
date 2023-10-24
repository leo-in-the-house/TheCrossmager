package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SanaFutaba extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SanaFutaba.class)
            .SetSkill(1, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public SanaFutaba()
    {
        super(DATA);

        Initialize(0, 12, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Brown(1);
    }


    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m)) {
            boolean hasCurse = false;

            for (AbstractCard card : player.hand.group) {
                if (card.type == CardType.CURSE) {
                    hasCurse = true;
                    break;
                }
            }

            if (!hasCurse) {
                for (AbstractCard card : player.exhaustPile.group) {
                    if (card.type == CardType.CURSE) {
                        hasCurse = true;
                        break;
                    }
                }
            }

            return hasCurse;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
    }
}