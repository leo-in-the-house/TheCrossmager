package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Bunnelby extends PokemonCard {
    public static final EYBCardData DATA = Register(Bunnelby.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Bunnelby() {
        super(DATA);

        Initialize(0, 5, 3);
        SetUpgrade(0, 3, 0);
        SetEvolution(new Diggersby());

        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryThorns(magicNumber);
    }
}