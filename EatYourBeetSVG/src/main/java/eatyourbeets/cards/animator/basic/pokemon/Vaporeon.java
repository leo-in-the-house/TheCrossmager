package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Vaporeon extends PokemonCard {
    public static final EYBCardData DATA = Register(Vaporeon.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Vaporeon() {
        super(DATA);

        Initialize(0, 10, 3);
        SetUpgrade(0, 3, 0);

        SetAffinity_Blue(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(VFX.Water(p.dialogX, p.dialogY));
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainPlatedArmor(magicNumber);
    }
}