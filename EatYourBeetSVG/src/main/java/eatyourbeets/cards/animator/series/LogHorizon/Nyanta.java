package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Nyanta extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Nyanta.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Piercing)
            .SetSeriesFromClassPackage();

    public Nyanta()
    {
        super(DATA);

        Initialize(3, 0, 1, 1);
        SetUpgrade(2, 0, 0, 1);

        SetAffinity_Green(2, 0, 1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        int numDelayedCards = 0;

        for (AbstractCard card : player.drawPile.group)
        {
            if (GameUtilities.IsDelayed(card)) {
                numDelayedCards += secondaryValue;
            }
        }

        GameUtilities.IncreaseMagicNumber(this, 1+numDelayedCards, true);

    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL)
            .SetVFX(true, false);
            GameActions.Bottom.StackPower(TargetHelper.Normal(m), GameUtilities.GetRandomElement(GameUtilities.GetCommonDebuffs()), 1)
            .ShowEffect(false, true);
        }
    }
}