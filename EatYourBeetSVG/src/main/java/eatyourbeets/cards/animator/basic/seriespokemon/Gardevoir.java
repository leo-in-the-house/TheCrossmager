package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Gardevoir extends PokemonCard {
    public static final EYBCardData DATA = Register(Gardevoir.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Gardevoir() {
        super(DATA);

        Initialize(0, 7, 3);
        SetUpgrade(0, 3, 0);

        SetAffinity_Pink(1);
        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainBlur(magicNumber);
    }
}