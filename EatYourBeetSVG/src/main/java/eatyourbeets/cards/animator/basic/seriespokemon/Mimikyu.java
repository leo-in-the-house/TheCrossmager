package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Mimikyu extends PokemonCard {
    public static final EYBCardData DATA = Register(Mimikyu.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Mimikyu() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetDelayed(true);
        SetExhaust(true);
        SetEthereal(true);

        SetAffinity_White(1);
        SetAffinity_Black(1);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();
        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        int numCurse = 0;

        for (AbstractCard card : player.hand.group) {
            if (card.type == CardType.CURSE) {
                numCurse++;
            }
        }

        if (numCurse > 0) {
            GameActions.Bottom.StackPower(new BufferPower(player, numCurse));
        }
    }
}