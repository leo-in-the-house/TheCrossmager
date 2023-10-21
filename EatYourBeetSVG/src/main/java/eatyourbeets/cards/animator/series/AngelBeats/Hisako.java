package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Hisako extends AnimatorCard {
    public static final EYBCardData DATA = Register(Hisako.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Hisako() {
        super(DATA);

        Initialize(0, 5, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_Yellow(2);

        SetEthereal(true);
    }


    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            boolean hasEthereal = false;

            for (AbstractCard card : player.hand.group) {
                if (card.isEthereal && !card.uuid.equals(this.uuid)) {
                    hasEthereal = true;
                    break;
                }
            }

            return hasEthereal;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}