package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Zigzagoon extends PokemonCard {
    public static final EYBCardData DATA = Register(Zigzagoon.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Zigzagoon() {
        super(DATA);

        Initialize(0, 8, 0);
        SetUpgrade(0, 3, 0);
        SetEvolution(new Linoone());

        SetAffinity_Violet(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}