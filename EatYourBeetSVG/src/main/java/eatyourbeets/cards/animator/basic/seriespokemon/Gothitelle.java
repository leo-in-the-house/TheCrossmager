package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Gothitelle extends PokemonCard {
    public static final EYBCardData DATA = Register(Gothitelle.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Gothitelle() {
        super(DATA);

        Initialize(0, 14, 0);
        SetUpgrade(0, 3, 0);

        SetAffinity_Pink(1);

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.Retain(name, 1, false);
    }
}