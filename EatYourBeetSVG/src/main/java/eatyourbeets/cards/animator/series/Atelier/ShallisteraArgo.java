package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.replacement.TemporaryRetainPower;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ShallisteraArgo extends AnimatorCard {
    public static final EYBCardData DATA = Register(ShallisteraArgo.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public ShallisteraArgo() {
        super(DATA);

        Initialize(0, 4, 2);
        SetUpgrade(0, 3, 1);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Brown(1);

        SetAffinityRequirement(Affinity.Blue, 2);
        SetAffinityRequirement(Affinity.Brown, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.SelectFromHand(name, 1, false)
            .SetOptions(true, true, true)
            .SetMessage(GR.Common.Strings.HandSelection.Copy)
            .AddCallback(cards ->
            {
                for (AbstractCard c : cards)
                {
                    GameActions.Top.MakeCardInHand(c.makeStatEquivalentCopy());
                }
            });
        }

        GameActions.Bottom.StackPower(new TemporaryRetainPower(p, magicNumber));
    }
}