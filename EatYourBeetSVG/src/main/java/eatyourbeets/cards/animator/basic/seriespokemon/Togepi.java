package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Togepi extends PokemonCard {
    public static final EYBCardData DATA = Register(Togepi.class)
            .SetSkill(0, CardRarity.BASIC, EYBCardTarget.None);

    public Togepi() {
        super(DATA);

        Initialize(0, 3, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_White(1);

        SetEthereal(true);
        SetExhaust(true);

        SetEvolution(new Togetic());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}