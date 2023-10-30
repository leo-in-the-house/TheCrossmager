package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.ChlammyZell_Scheme;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;

public class ChlammyZell extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ChlammyZell.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new ChlammyZell_Scheme(), false));

    public ChlammyZell()
    {
        super(DATA);

        Initialize(0, 0, 2, 4);
        SetUpgrade(0, 0, 1, 1);

        SetAffinity_Pink(1);
        SetAffinity_Violet(1);

        SetAffinityRequirement(Affinity.Pink, 3);
        SetAffinityRequirement(Affinity.Violet, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DrawNextTurn(magicNumber);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.MakeCardInHand(new ChlammyZell_Scheme());
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return super.CheckSpecialConditionLimited(tryUse, super::CheckSpecialCondition);
    }
}