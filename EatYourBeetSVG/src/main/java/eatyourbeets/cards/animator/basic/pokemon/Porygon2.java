package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Porygon2 extends PokemonCard {
    public static final EYBCardData DATA = Register(Porygon2.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Porygon2() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 0);

        SetAffinity_Teal(1);
        SetEvolution(new PorygonZ());

        SetPurge(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Callback(() -> {
            for (AbstractCard c : player.hand.group) {
                if (c instanceof AnimatorCard) {
                    for (EYBCardAffinity affinity : GameUtilities.GetAffinities(c).List) {
                        if (affinity.type != Affinity.Sealed) {
                            if (affinity.level < 2) {
                                affinity.level++;
                            }
                        }

                        if (affinity.scaling > 0) {
                            affinity.scaling += magicNumber;
                        }
                    }
                }
            }
        });
    }
}