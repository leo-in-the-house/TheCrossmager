package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ralts extends PokemonCard {
    public static final EYBCardData DATA = Register(Ralts.class)
            .SetSkill(0, CardRarity.BASIC, EYBCardTarget.None);

    public Ralts() {
        super(DATA);

        Initialize(0, 2, 0);
        SetUpgrade(0, 2, 0);

        SetAffinity_Pink(1);
        SetAffinity_White(1);

        SetEvolution(new Kirlia());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}