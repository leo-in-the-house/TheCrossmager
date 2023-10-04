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
            .SetAttack(0, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Noda()
    {
        super(DATA);

        Initialize(3, 0, 2, 0);
        SetUpgrade(2, 0, 0, 0);

        SetAffinity_Black(1);
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

        int numEthereal = 0;

        for (AbstractCard card : player.exhaustPile.group) {
            if (card.isEthereal) {
                numEthereal++;
            }
        }

        if (numEthereal < 2)
        {
            GameActions.Last.Exhaust(this);
        }
    }
}