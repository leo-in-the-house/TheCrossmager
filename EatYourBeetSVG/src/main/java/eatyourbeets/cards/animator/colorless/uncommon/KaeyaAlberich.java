package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KaeyaAlberich extends AnimatorCard {
    public static final EYBCardData DATA = Register(KaeyaAlberich.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public KaeyaAlberich() {
        super(DATA);

        Initialize(0, 4, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Blue(1);
    }

    public AbstractAttribute GetBlockInfo()
    {
        if (GameUtilities.InGame()) {
            return super.GetBlockInfo().AddMultiplier(1+player.filledOrbCount());
        }

        return super.GetBlockInfo().AddMultiplier(1);
    }


    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.ChannelOrb(new Frost());
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();

        GameActions.Bottom.ChannelOrb(new Frost());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        for (int i=0; i<player.filledOrbCount(); i++) {
            GameActions.Bottom.GainBlock(block);
        }
    }
}