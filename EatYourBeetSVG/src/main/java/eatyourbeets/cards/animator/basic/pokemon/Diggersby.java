package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Diggersby extends PokemonCard {
    public static final EYBCardData DATA = Register(Diggersby.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Diggersby() {
        super(DATA);

        Initialize(0, 12, 9);
        SetUpgrade(0, 3, 0);

        SetAffinity_Brown(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryThorns(magicNumber);
    }
}