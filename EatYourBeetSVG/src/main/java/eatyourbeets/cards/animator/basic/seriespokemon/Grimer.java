package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.animator.status.Status_Slimed;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Grimer extends PokemonCard {
    public static final EYBCardData DATA = Register(Grimer.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None)
            .PostInitialize(data -> data.AddPreview(new Status_Slimed(), false));
    public Grimer() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Violet(1);
        SetAffinity_Black(1);

        SetEvolution(new Muk());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.EvokeOrb(magicNumber);
        GameActions.Bottom.MakeCardInHand(new Status_Slimed());
    }
}