package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Jigglypuff extends PokemonCard {
    public static final EYBCardData DATA = Register(Jigglypuff.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.ALL);

    public Jigglypuff() {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 3);

        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ApplyWeak(TargetHelper.Enemies(), magicNumber);
    }
}