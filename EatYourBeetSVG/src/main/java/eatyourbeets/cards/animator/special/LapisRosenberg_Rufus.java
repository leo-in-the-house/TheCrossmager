package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.LegendOfHeroesTrails.LapisRosenberg;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class LapisRosenberg_Rufus extends AnimatorCard {
    public static final EYBCardData DATA = Register(LapisRosenberg_Rufus.class)
            .SetAttack(3, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public LapisRosenberg_Rufus() {
        super(DATA);

        Initialize(20, 0, 0);
        SetUpgrade(9, 0, 0);

        SetAffinity_Black(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);
        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);

        int numLapis = GetNumLapisCopiesEverywhere();

        if (numLapis > 0) {
            GameActions.Bottom.EvokeOrb(numLapis);
        }
    }

    private int GetNumLapisCopiesEverywhere() {
        return GetNumLapisCopiesInPile(player.hand)
                + GetNumLapisCopiesInPile(player.drawPile)
                + GetNumLapisCopiesInPile(player.discardPile)
                + GetNumLapisCopiesInPile(player.exhaustPile);
    }

    private int GetNumLapisCopiesInPile(CardGroup group) {
        int count = 0;

        for (AbstractCard card : group.group) {
            if (card.cardID.equals(LapisRosenberg.DATA.ID)) {
                count++;
            }
        }

        return count;
    }
}