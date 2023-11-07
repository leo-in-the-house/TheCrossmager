package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Godan extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Godan.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Godan()
    {
        super(DATA);

        Initialize(7, 0);
        SetUpgrade(4, 0);

        SetAffinity_Red(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        boolean hasEthereal = false;

        for (AbstractCard card : player.hand.group) {
            if (card.isEthereal) {
                hasEthereal = true;
                break;
            }
        }

        if (hasEthereal)
        {
            GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
        }
    }
}