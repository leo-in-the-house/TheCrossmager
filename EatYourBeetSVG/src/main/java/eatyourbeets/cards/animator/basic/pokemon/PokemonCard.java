package eatyourbeets.cards.animator.basic.pokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.CardTooltips;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.AdvancedTexture;
import eatyourbeets.utilities.GameEffects;

import java.util.ArrayList;

/**
 * Pokemon Cards are basic cards that evolve when you enter a new act if there is a valid evolution.
 * Evolution permanently transforms the card in your master deck into the evolved card.
 * Legendary Pokemon and Series Pokemon cannot be added automatically like most basics, as well as evolved Pokemon.
 * Otherwise, the Pokemon is upgraded normally.
 */
public class PokemonCard extends AnimatorCard {

    private PokemonCard evolution;
    private boolean evolved;
    private boolean legendary;
    private boolean series;
    private final Color borderColor = Color.RED;
    public PokemonCard(EYBCardData data) {
        this(data, false, null);
    }

    public PokemonCard(EYBCardData data, PokemonCard evolution) {
        this(data, false, evolution);
    }

    public PokemonCard(EYBCardData data, boolean evolved) {
        this(data, evolved, null);
    }

    public PokemonCard(EYBCardData data, boolean evolved, PokemonCard evolution) {
        super(data);

        if (evolution != null){
            SetEvolution(evolution);
        }

        tooltips.add(CardTooltips.FindByName(GR.Animator.PlayerClass, "pokemon"));
        SetEvolved(evolved);

        SetSeries(CardSeries.Pokemon);

        SetTag(GR.Enums.CardTags.POKEMON_CARD, true);
    }

    public static ArrayList<EYBCardData> GetCards(AnimatorCard emblemicPokemon)
    {
        ArrayList<EYBCardData> cards = new ArrayList<>();

        //Remember to add any new Pokemon here if you want them to be available in a loadout!
        cards.add(Litleo.DATA);
        cards.add(Smoliv.DATA);

        if (emblemicPokemon != null) {
            cards.add(emblemicPokemon.cardData);
        }

        MarkAllStarterPokemonAsSeen(cards);

        return cards;
    }

    private static void MarkAllStarterPokemonAsSeen(ArrayList<EYBCardData> pokemonCards) {
        for (EYBCardData cardData : pokemonCards)
        {
            AbstractCard card = CardLibrary.getCard(cardData.ID);

            if (!card.isSeen)
            {
                UnlockTracker.markCardAsSeen(card.cardID);
            }
        }
    }

    public static void EvolveAllPokemon() {
        ArrayList<AbstractCard> cardsToRemove = new ArrayList<>();

        for (AbstractCard card : player.masterDeck.group) {
            if (card instanceof PokemonCard) {
                PokemonCard pokemon = (PokemonCard) card;
                if (pokemon.evolution != null) {
                    cardsToRemove.add(pokemon);
                    AbstractCard evoPokemon = pokemon.evolution.makeCopy();
                    if (pokemon.upgraded) {
                        evoPokemon.upgrade();
                    }
                    GameEffects.TopLevelQueue.ShowAndObtain(evoPokemon);
                }
            }
        }

        for (AbstractCard card : cardsToRemove) {
            player.masterDeck.removeCard(card.cardID);
        }
    }

    public void SetEvolution(PokemonCard evolution) {
        this.evolution = evolution;

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
        return evolution != null;
    }

    public EYBCard GetEvolution() {
        return evolution;
    }

    @Override
    protected AdvancedTexture GetCardBanner()
    {
        return super.GetCardBanner().SetColor(borderColor);
    }

    @Override
    protected AdvancedTexture GetPortraitFrame()
    {
        return super.GetPortraitFrame().SetColor(borderColor);
    }
}
