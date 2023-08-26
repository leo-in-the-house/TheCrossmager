package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Godan extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Godan.class).SetAttack(1, CardRarity.COMMON)
            .SetSeriesFromClassPackage();

    public Godan()
    {
        super(DATA);

        Initialize(7, 0, 2);
        SetUpgrade(4, 0, 1);

    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        boolean hasEthereal = false;

        for (AbstractCard card : player.hand.group) {
            if (card.isEthereal) {
                hasEthereal = true;
                break;
            }
        }

        if (hasEthereal && CombatStats.TryActivateSemiLimited(cardID))
        {
            GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
        }
    }
}