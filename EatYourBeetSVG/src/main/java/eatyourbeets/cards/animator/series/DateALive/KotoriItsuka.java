package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class KotoriItsuka extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KotoriItsuka.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Normal)
            .SetSeriesFromClassPackage();

    public KotoriItsuka()
    {
        super(DATA);

        Initialize(5, 0, 5, 5);
        SetUpgrade(0, 0, 0);

        SetAffinity_Red(2);

        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.Add(new ShakeScreenAction(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.MED));

        for (int i=0; i<magicNumber; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.FIRE);
        }

        int amount_burning = (int) Math.ceil(player.currentBlock / 2f);

        if (upgraded) {
            amount_burning *= 2;
        }

        if (amount_burning > 0) {
            GameActions.Bottom.LoseBlock(amount_burning);
            GameActions.Bottom.ApplyBurning(TargetHelper.Normal(m), amount_burning);
        }
    }
}