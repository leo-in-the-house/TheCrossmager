package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Sinistea extends PokemonCard {
    public static final EYBCardData DATA = Register(Sinistea.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Sinistea() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Black(1);
        SetAffinity_Blue(1);

        SetEvolution(new Polteageist());
    }

    @Override
    protected void OnUpgrade()
    {
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Callback(() -> {
            RandomizedList<AbstractCard> possibleCards = new RandomizedList<>();

            for (AbstractCard card : player.hand.group) {
                if (card.rarity == CardRarity.COMMON || card.rarity == CardRarity.BASIC) {
                    possibleCards.Add(card);
                }
            }

            if (possibleCards.Size() > 0) {
                AbstractCard card = possibleCards.Retrieve(rng);

                if (card != null) {
                    GameActions.Top.MakeCardInHand(GameUtilities.Imitate(card));
                }
            }
        });
    }
}