package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class IsuzuTonan extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IsuzuTonan.class)
            .SetSkill(3, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    private static final CardEffectChoice choices = new CardEffectChoice();

    public IsuzuTonan()
    {
        super(DATA);

        Initialize(0, 4, 2);
        SetUpgrade(0, 1, 0);

        SetAffinity_Yellow(2);

        SetFading(true);
        SetDelayed(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.GainEnergy(player.hand.group.size());
    }
}