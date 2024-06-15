package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Linoone extends PokemonCard {
    public static final EYBCardData DATA = Register(Linoone.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Linoone() {
        super(DATA);

        Initialize(0, 20, 0);
        SetUpgrade(0, 3, 0);
        SetEvolution(new Obstagoon());

        SetAffinity_Violet(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}