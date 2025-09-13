package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.LegendOfHeroesTrails.EstelleBright;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class EstelleBright_Joshua extends AnimatorCard {
    public static final EYBCardData DATA = Register(EstelleBright_Joshua.class)
            .SetAttack(0, CardRarity.SPECIAL, EYBAttackType.Piercing, EYBCardTarget.Normal)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public EstelleBright_Joshua() {
        super(DATA);

        Initialize(4, 0, 2);
        SetUpgrade(1, 0, 1);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Black(1, 0, 1);

        SetExhaust(true);
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);

        GameActions.Bottom.ApplyShackles(m, magicNumber);
        GameActions.Bottom.ApplyLockOn(TargetHelper.Normal(m), magicNumber);

        if (GetNumJoshEverywhere() <= 0) {
            GameActions.Bottom.FetchFromPile(name, 1, p.exhaustPile)
                .SetOptions(false, true)
                .SetFilter(card -> card.cardID.equals(EstelleBright.DATA.ID))
                .SetMessage(GR.Common.Strings.HandSelection.Obtain)
                .AddCallback(cards -> {
                    for (AbstractCard card : cards) {
                        GameActions.Top.Motivate(card, 1);
                    }
                });
        }
    }


    private int GetNumJoshEverywhere() {
        return GetNumJoshInPile(player.hand)
                + GetNumJoshInPile(player.drawPile)
                + GetNumJoshInPile(player.discardPile);
    }

    private int GetNumJoshInPile(CardGroup group) {
        int count = 0;

        for (AbstractCard card : group.group) {
            if (card.cardID.equals(EstelleBright_Joshua.DATA.ID) && !card.uuid.equals(this.uuid)) {
                count++;
            }
        }

        return count;
    }
}