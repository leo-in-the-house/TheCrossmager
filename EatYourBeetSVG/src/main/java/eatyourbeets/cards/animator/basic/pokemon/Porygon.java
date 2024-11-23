package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Porygon extends PokemonCard {
    public static final EYBCardData DATA = Register(Porygon.class)
            .SetSkill(0, CardRarity.BASIC, EYBCardTarget.None);

    public Porygon() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Teal(1);
        SetEvolution(new Porygon2());

        SetPurge(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.SelectFromHand(name, 1, false)
        .SetOptions(false, false, false)
        .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
        .SetFilter(card -> card instanceof AnimatorCard)
        .AddCallback(cards -> {
            if (cards.size() > 0) {
                AbstractCard c = cards.get(0);
                if (c instanceof AnimatorCard) {
                    for (EYBCardAffinity affinity : GameUtilities.GetAffinities(c).List) {
                        if (affinity.type != Affinity.Sealed) {
                            if (affinity.level < 2) {
                                affinity.level++;
                            }
                        }
                    }
                }
            }
        });
    }
}