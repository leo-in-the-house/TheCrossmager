package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Bidoof extends PokemonCard {
    public static final EYBCardData DATA = Register(Bidoof.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Bidoof() {
        super(DATA);

        Initialize(0, 0, 4);
        SetUpgrade(0, 0, 3);
        SetEvolution(new Bibarel());

        SetAffinity_Blue(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryArtifact(magicNumber);
    }
}