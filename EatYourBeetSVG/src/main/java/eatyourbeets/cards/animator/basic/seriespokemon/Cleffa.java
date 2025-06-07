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

public class Cleffa extends PokemonCard {
    public static final EYBCardData DATA = Register(Cleffa.class)
            .SetSkill(0, CardRarity.BASIC, EYBCardTarget.None);

    public Cleffa() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_White(1);
        SetAffinity_Pink(1);

        SetExhaust(true);
        SetEvolution(new Clefairy());
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

        GameActions.Bottom.Draw(1)
                .SetFilter(card -> GameUtilities.IsSeries(card, CardSeries.Konosuba), false);
    }
}