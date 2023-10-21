package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Naotsugu extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Naotsugu.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Normal)
            .SetSeriesFromClassPackage();

    public Naotsugu()
    {
        super(DATA);

        Initialize(6, 6);
        SetUpgrade(4, 4);

        SetAffinity_Red(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);
        GameActions.Bottom.GainBlock(block);

        if (player.drawPile.size() > 0)
        {
            AbstractCard card = player.drawPile.getBottomCard();

            if (card != null) {
                GameActions.Bottom.Draw(card);
            }
        }
    }
}