package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Noda extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Noda.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Noda()
    {
        super(DATA);

        Initialize(3, 0, 2, 0);
        SetUpgrade(0, 0, 1, 0);
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

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        }

        boolean hasEthereal = false;

        for (AbstractCard card : player.hand.group) {
            if (card.isEthereal) {
                hasEthereal = true;
                break;
            }
        }

        if (!hasEthereal)
        {
            GameActions.Last.Exhaust(this);
        }
    }
}