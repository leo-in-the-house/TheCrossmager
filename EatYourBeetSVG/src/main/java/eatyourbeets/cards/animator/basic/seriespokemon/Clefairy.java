package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Clefairy extends PokemonCard {
    public static final EYBCardData DATA = Register(Clefairy.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Clefairy() {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 0);

        SetAffinity_White(1);
        SetAffinity_Pink(1);

        SetExhaust(true);
        SetEvolution(new Clefable());
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.Draw(magicNumber)
                .SetFilter(card -> GameUtilities.IsSeries(card, CardSeries.Konosuba), false);
    }
}