package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class PorygonZ extends PokemonCard {
    public static final EYBCardData DATA = Register(PorygonZ.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public PorygonZ() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_Teal(1);

        SetPurge(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Callback(() -> {
            SetStarAndUpgradeScaling(player.hand);
            SetStarAndUpgradeScaling(player.exhaustPile);
            SetStarAndUpgradeScaling(player.drawPile);
            SetStarAndUpgradeScaling(player.discardPile);
        });
    }

    private void SetStarAndUpgradeScaling(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c instanceof AnimatorCard) {
                GameActions.Top.IncreaseScaling(c, Affinity.Star, 1);

                for (EYBCardAffinity affinity : GameUtilities.GetAffinities(c).List) {
                    if (affinity.scaling > 0) {
                        affinity.scaling += magicNumber;
                    }
                }
            }
        }
    }
}