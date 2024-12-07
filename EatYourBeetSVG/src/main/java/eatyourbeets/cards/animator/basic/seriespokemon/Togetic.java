package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Togetic extends PokemonCard {
    public static final EYBCardData DATA = Register(Togetic.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Togetic() {
        super(DATA);

        Initialize(0, 15, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_White(1);

        SetEthereal(true);
        SetExhaust(true);

        SetEvolution(new Togekiss());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}