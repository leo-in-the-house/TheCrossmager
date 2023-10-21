package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MiyukiIrie extends AnimatorCard {
    public static final EYBCardData DATA = Register(MiyukiIrie.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeries(CardSeries.AngelBeats);

    public MiyukiIrie() {
        super(DATA);

        Initialize(0, 2, 2, 2);
        SetUpgrade(0, 1, 0, 1);

        SetAffinity_Yellow(2, 0, 2);

        SetEthereal(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Top.PlayCopy(this, null);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.GainYellow(secondaryValue);
    }
}