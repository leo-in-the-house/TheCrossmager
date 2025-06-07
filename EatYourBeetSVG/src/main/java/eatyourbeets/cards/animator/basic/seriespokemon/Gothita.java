package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Gothita extends PokemonCard {
    public static final EYBCardData DATA = Register(Gothita.class)
            .SetSkill(0, CardRarity.BASIC, EYBCardTarget.None);

    public Gothita() {
        super(DATA);

        Initialize(0, 2, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_Pink(1);

        SetRetain(true);
        SetEvolution(new Gothorita());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}