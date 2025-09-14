package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import eatyourbeets.cards.animator.special.LapisRosenberg_Rufus;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class LapisRosenberg extends AnimatorCard {
    public static final EYBCardData DATA = Register(LapisRosenberg.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new LapisRosenberg_Rufus(), true);
            });

    public LapisRosenberg() {
        super(DATA);

        Initialize(0, 2, 0);
        SetUpgrade(0, 1, 0);

        SetAffinity_Teal(1);
        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (HasTwoAdjacentSameOrbs() && CombatStats.TryActivateSemiLimited(cardID)) {
            GameActions.Bottom.MakeCardInDrawPile(this.makeStatEquivalentCopy())
                .Repeat(2)
                .AddCallback(cards -> {
                    if (GetNumLapisCopiesEverywhere() >= 6 && CombatStats.TryActivateLimited(cardID)) {
                        GameActions.Bottom.MakeCardInHand(new LapisRosenberg_Rufus());
                    }
                });
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

    private boolean HasTwoAdjacentSameOrbs() {

        int curMode = 0;
        AbstractOrb previousOrb = null;

        for (AbstractOrb orb : player.orbs) {
            if (orb.ID == null) {
                curMode = 0;
                continue;
            }

            if (previousOrb == null || !orb.ID.equals(previousOrb.ID)) {
                previousOrb = orb;
                curMode = 0;
            }

            curMode++;
            if (curMode >= 2) {
                return true;
            }
        }

        return false;
    }
}