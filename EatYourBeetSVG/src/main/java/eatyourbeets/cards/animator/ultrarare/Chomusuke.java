package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Chomusuke extends AnimatorCard_UltraRare implements OnAddToDeckListener
{
    public static final EYBCardData DATA = Register(Chomusuke.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Konosuba);

    public Chomusuke()
    {
        super(DATA);

        Initialize(0, 0, 1, 2);
        SetUpgrade(0, 0, 1, 1);

        SetAffinity_Black(1);
        SetAffinity_Pink(1);
    }

    @Override
    public boolean OnAddToDeck()
    {
        CardGroup konosubaCards = GetKonosubaCards();
        List<AbstractCard> cardsToRemove = new LinkedList<>();

        for (AbstractCard card : player.masterDeck.group) {
            if (card.rarity == CardRarity.BASIC) {
                cardsToRemove.add(card);
            }
        }

        for (AbstractCard card : cardsToRemove) {
            card.untip();
            card.unhover();
            player.masterDeck.removeCard(card);

            GameEffects.TopLevelList.ShowAndObtain(konosubaCards.getRandomCard(rng).makeCopy());
        }

        return true;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainEnergy(magicNumber);
        GameActions.Bottom.Draw(secondaryValue);
    }

    private CardGroup GetKonosubaCards() {

        ArrayList<AnimatorCard> konosubaCards = new ArrayList<>();
        CardGroup konosubaCardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        CardSeries konosuba = CardSeries.GetByName("Konosuba", false);

        if (konosuba != null)
        {
            CardSeries.Affinity.AddCards(konosuba, CardLibrary.getAllCards(), konosubaCards);
        }

        for (AnimatorCard card : konosubaCards) {
            if (!(card instanceof AnimatorCard_UltraRare) && card.rarity != CardRarity.SPECIAL) {
                card.upgrade();
                konosubaCardGroup.addToTop(card);
            }
        }

        return konosubaCardGroup;
    }
}