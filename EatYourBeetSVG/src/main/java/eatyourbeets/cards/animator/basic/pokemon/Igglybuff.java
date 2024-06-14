package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Igglybuff extends PokemonCard {
    public static final EYBCardData DATA = Register(Igglybuff.class)
            .SetSkill(0, CardRarity.BASIC, EYBCardTarget.Normal);

    public Igglybuff() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 2);
        SetEvolution(new Jigglypuff());

        SetAffinity_White(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ApplyWeak(player, m, magicNumber);
    }
}