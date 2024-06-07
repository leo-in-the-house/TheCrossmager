package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Elysia_Herrscher extends AnimatorCard {
    public static final EYBCardData DATA = Register(Elysia_Herrscher.class)
            .SetSkill(-1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.HonkaiImpact3rd);

    public Elysia_Herrscher() {
        super(DATA);

        Initialize(0, 9, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_White(2, 0, 2);
        SetAffinity_Blue(2, 0, 2);

        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(GameUtilities.GetPotentialXCostEnergy(this));
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);


        int stacks = GameUtilities.UseXCostEnergy(this);

        for (int i=0; i<stacks; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.GainArtifact(99);
    }
}