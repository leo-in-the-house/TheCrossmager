package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Bibarel extends PokemonCard {
    public static final EYBCardData DATA = Register(Bibarel.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Bibarel() {
        super(DATA);

        Initialize(0, 17, 4);
        SetUpgrade(0, 0, 3);

        SetAffinity_Blue(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryArtifact(magicNumber);
    }
}