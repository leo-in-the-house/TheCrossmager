package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Wigglytuff extends PokemonCard {
    public static final EYBCardData DATA = Register(Wigglytuff.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.ALL);

    public Wigglytuff() {
        super(DATA);

        Initialize(0, 20, 5);
        SetUpgrade(0, 0, 3);

        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ApplyWeak(TargetHelper.Enemies(), magicNumber);
    }
}