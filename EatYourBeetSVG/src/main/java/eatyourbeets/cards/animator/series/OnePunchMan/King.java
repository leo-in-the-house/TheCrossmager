package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class King extends AnimatorCard
{
    public static final EYBCardData DATA = Register(King.class)
            .SetSkill(UNPLAYABLE_COST, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public King()
    {
        super(DATA);

        Initialize(0, 0);
        SetUpgrade(0, 0);

        SetAffinity_Brown(1, 1, 0);
        SetUnplayable(true);
        SetEthereal(true);
        SetDelayed(true);
    }
    @Override
    protected void OnUpgrade()
    {
        SetDelayed(false);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        super.triggerOnOtherCardPlayed(c);

        if (GameUtilities.HasAnyScaling(c))
        {
            GameActions.Top.StackPower(TargetHelper.RandomEnemy(), GameUtilities.GetRandomElement(GameUtilities.GetCommonDebuffs()), 1)
                    .ShowEffect(true, true);
            GameActions.Top.Flash(this);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

    }
}