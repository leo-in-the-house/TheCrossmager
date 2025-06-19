package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Wattrel extends PokemonCard {
    public static final EYBCardData DATA = Register(Wattrel.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.Normal);

    public Wattrel() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 0);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ChannelOrb(new Lightning());
        GameActions.Bottom.ApplyLockOn(p, m, magicNumber);
    }
}