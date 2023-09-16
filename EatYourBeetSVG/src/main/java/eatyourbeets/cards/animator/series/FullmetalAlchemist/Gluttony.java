package eatyourbeets.cards.animator.series.FullmetalAlchemist;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.HPAttribute;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Gluttony extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Gluttony.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Gluttony()
    {
        super(DATA);

        Initialize(0, 0, 6, 4);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Yellow(1);
        SetAffinity_Violet(1);

        SetHealing(true);
        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return HPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Top.Heal(magicNumber);

        if (CheckSpecialCondition(false))
        {
            GameActions.Top.MoveCards(p.drawPile, p.exhaustPile, secondaryValue)
            .ShowEffect(true, true)
            .SetOrigin(CardSelection.Top)
            .AddCallback(cards ->
            {
                int evokeAmount = 0;

                for (AbstractCard c : cards)
                {
                    GameActions.Top.SealAffinities(c, false);
                    evokeAmount++;
                }

                GameActions.Top.EvokeOrb(evokeAmount);
            });
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return player.drawPile.size() >= secondaryValue;
    }
}