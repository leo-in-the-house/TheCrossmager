package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;
import java.util.UUID;

public class Traveler extends AnimatorCard_UltraRare implements OnAddToDeckListener {
    public static final EYBCardData DATA = Register(Traveler.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public Traveler() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Star(2);

        SetObtainableInCombat(false);
        SetHealing(true);
    }

    @Override
    public boolean OnAddToDeck()
    {
        final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.group.add(new Traveler_Aether());
        group.group.add(new Traveler_Lumine());

        GameActions.Bottom.SelectFromPile(name, 1, group)
        .SetOptions(false, false)
        .AddCallback(cards ->
        {
            if (cards != null && cards.size() > 0)
            {
                AbstractCard newCard = cards.get(0);
                if (upgraded) {
                    newCard.upgrade();
                }
                ReplaceCardInMasterDeck(uuid, newCard);
            }

            AddGenshinImpactCardsToDeck();
        });

        return true;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);
    }

    private void ReplaceCardInMasterDeck(UUID uuid, AbstractCard newCard) {
        CardGroup masterDeck = player.masterDeck;

        for (int i = 0; i < masterDeck.group.size(); i++)
        {
            AbstractCard original = masterDeck.group.get(i);
            if (uuid.equals(original.uuid))
            {
                masterDeck.removeCard(original);
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(newCard, (float) Settings.WIDTH / 3.0F, (float) Settings.HEIGHT / 2.0F, false));
                break;
            }
        }
    }

    private void AddGenshinImpactCardsToDeck() {
        ArrayList<AnimatorCard> genshinImpactCards = new ArrayList<>();
        CardGroup genshinImpactCardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        CardSeries genshinImpact = CardSeries.GetByName("GenshinImpact", false);

        if (genshinImpact != null)
        {
            CardSeries.Affinity.AddCards(genshinImpact, CardLibrary.getAllCards(), genshinImpactCards);
        }

        for (AnimatorCard card : genshinImpactCards) {
            if (!(card instanceof AnimatorCard_UltraRare) && card.rarity != CardRarity.SPECIAL) {
                card.upgrade();
                genshinImpactCardGroup.addToTop(card);
            }
        }

        GameActions.Bottom.SelectFromPile(name, 5, genshinImpactCardGroup)
                .SetOptions(false, false)
                .AddCallback(cards ->
                {
                    if (cards != null && cards.size() > 0)
                    {
                        CardGroup masterDeck = player.masterDeck;
                        for (AbstractCard card : cards) {
                            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(card, (float) Settings.WIDTH / 3.0F, (float) Settings.HEIGHT / 2.0F, false));
                        }
                    }
                });
    }
}