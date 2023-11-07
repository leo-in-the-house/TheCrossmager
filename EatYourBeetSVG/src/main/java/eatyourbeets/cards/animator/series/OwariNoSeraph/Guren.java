package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.GurenAction;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Guren extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Guren.class)
            .SetSkill(2, CardRarity.RARE)
            .SetSeries(CardSeries.OwariNoSeraph);

    public Guren()
    {
        super(DATA);

        Initialize(0, 0,3);
        SetUpgrade(0, 0,2);

        SetAffinity_Pink(1);
        SetAffinity_Violet(1);
        SetAffinity_Brown(1);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetExhaust(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.Add(new GurenAction(m));
        }
    }
}