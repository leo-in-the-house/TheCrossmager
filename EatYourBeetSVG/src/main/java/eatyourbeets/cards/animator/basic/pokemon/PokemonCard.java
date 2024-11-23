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
    private boolean hasSpecialEvolution;
    private boolean legendary;
    private boolean series;
    private final Color borderColor = Color.RED;
    public PokemonCard(EYBCardData data) {
        this(data, null);
    }

    public PokemonCard(EYBCardData data, PokemonCard evolution) {
        super(data);

        if (evolution != null){
            SetEvolution(evolution);
        }

        SetSeries(CardSeries.Pokemon);

        SetTag(GR.Enums.CardTags.POKEMON_CARD, true);
    }


    @Override
    public void initializeDescription()
    {
        super.initializeDescription();

        if (cardText != null)
        {
            tooltips.add(CardTooltips.FindByName(GR.Animator.PlayerClass, "pokemon"));
        }
    }

    public static ArrayList<EYBCardData> GetStarterCards(AnimatorCard emblemicPokemon)
    {
        ArrayList<EYBCardData> cards = new ArrayList<>();

        //Remember to add any new Pokemon here if you want them to be available in a loadout!
        cards.add(Litleo.DATA);
        cards.add(Smoliv.DATA);
        cards.add(Bidoof.DATA);
        cards.add(Igglybuff.DATA);
        cards.add(Zorua.DATA);
        cards.add(Helioptile.DATA);
        cards.add(Stantler.DATA);
        cards.add(Bunnelby.DATA);
        cards.add(Zigzagoon.DATA);
        cards.add(Porygon.DATA);
        cards.add(Eevee.DATA);

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
                if (pokemon.evolution != null || pokemon.hasSpecialEvolution) {
                    pokemon.Evolve();
                }
                cardsToRemove.add(pokemon);
            }
        }

        for (AbstractCard card : cardsToRemove) {
            player.masterDeck.removeCard(card.cardID);
        }
    }

    //Override this method to implement custom evolutions
    //Note that no matter what happens, the Pokemon card is removed from the deck automatically afterwards.
    //Set hasSpecialEvolution to true on the Pokemon card if you do not wish to use PokemonCard.evolution.
    public void Evolve() {
        EvolveInto(evolution);
    }

    protected void EvolveInto(PokemonCard target) {
            AbstractCard evoPokemon = target.makeCopy();
            if (upgraded || target.upgraded) {
                evoPokemon.upgrade();
            }
            GameEffects.TopLevelQueue.ShowAndObtain(evoPokemon);
    }

    public void SetEvolution(PokemonCard evolution) {
        this.evolution = evolution;

        cardData.AddPreview(evolution.makeCopy(), false);
    }

    public void SetAsSeriesPokemon() {
        series = true;
    }

    public void HasSpecialEvolution() {
        hasSpecialEvolution = true;
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
