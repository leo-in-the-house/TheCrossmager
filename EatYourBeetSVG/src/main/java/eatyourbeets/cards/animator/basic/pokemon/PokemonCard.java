package eatyourbeets.cards.animator.basic.pokemon;

import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.GR;

/**
 * Pokemon Cards are basic cards that evolve instead of being upgraded if there is a valid evolution.
 * Evolution permanently transforms the card in your master deck into the evolved card.
 * Legendary Pokemon and Series Pokemon cannot be added automatically like most basics, as well as evolved Pokemon.
 * Otherwise, the Pokemon is upgraded normally.
 */
public class PokemonCard extends AnimatorCard {

    private boolean evolved;
    private boolean legendary;
    private boolean series;
    public PokemonCard(EYBCardData data) {
        this(data, false, null);
    }

    public PokemonCard(EYBCardData data, AnimatorCard evolution) {
        this(data, false, evolution);
    }

    public PokemonCard(EYBCardData data, boolean evolved) {
        this(data, evolved, null);
    }

    public PokemonCard(EYBCardData data, boolean evolved, AnimatorCard evolution) {
        super(data);

        SetEvolution(evolution);
        SetEvolved(evolved);

        SetSeries(CardSeries.Pokemon);

        SetTag(GR.Enums.CardTags.POKEMON_CARD, true);
    }

    public void SetEvolution(AnimatorCard evolution) {
        this.upgradeForm = evolution;

        cardData.AddPreview(evolution.makeCopy(), false);
    }

    public void SetEvolved(boolean evolved) {
        this.evolved = evolved;
    }

    public boolean ObtainableFromLoadout() {
        return !series && !legendary && !evolved;
    }

    public void SetAsSeriesPokemon() {
        series = true;
    }

    public void SetAsLegendaryPokemon() {
        legendary = true;
    }

    public boolean HasEvolution() {
        return upgradeForm != null;
    }

    public EYBCard GetEvolution() {
        return upgradeForm;
    }
}
