package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.special.RefreshHandLayout;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Cofagrigus extends PokemonCard {
    public static final EYBCardData DATA = Register(Cofagrigus.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Cofagrigus() {
        super(DATA);

        Initialize(0, 10, 0);
        SetUpgrade(0, 2, 0);

        SetAffinity_Black(1);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.MakeCardInHand(this.makeStatEquivalentCopy())
            .AddCallback(card ->
            {
                GameUtilities.ModifyCostForTurn(card, 1, true);
                GameActions.Bottom.Add(new RefreshHandLayout());
            });
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}