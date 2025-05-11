package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.special.RefreshHandLayout;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Yamask extends PokemonCard {
    public static final EYBCardData DATA = Register(Yamask.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Yamask() {
        super(DATA);

        Initialize(0, 4, 0);
        SetUpgrade(0, 2, 0);

        SetAffinity_Black(1);
        SetEvolution(new Cofagrigus());
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        if (CombatStats.TryActivateSemiLimited(cardID)) {
            GameActions.Bottom.MakeCardInHand(this.makeStatEquivalentCopy())
                .AddCallback(card ->
                {
                    GameActions.Bottom.Motivate(card, 1);
                    GameActions.Bottom.Add(new RefreshHandLayout());
                });
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}