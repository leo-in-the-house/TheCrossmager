package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Melzalgald_G extends MelzalgaldAlt
{
    public static final EYBCardData DATA = Register(Melzalgald_G.class)
            .SetAttack(1, CardRarity.SPECIAL)
            .SetSeries(SERIES);

    public Melzalgald_G()
    {
        super(DATA);

        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);

        for (AbstractCard card : player.hand.group) {
            if (GameUtilities.HasDamageOrBlock(card)) {
                GameActions.Bottom.IncreaseScaling(card, Affinity.Green, 1);
            }
        }
    }
}