package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.*;

public class Viola extends AnimatorCard {
    public static final EYBCardData DATA = Register(Viola.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TheWitchsHouse);

    public Viola() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Yellow(1);
        SetAffinity_White(1);

        SetPurge(true);
        SetDelayed(true);
        SetHealing(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(new RainbowCardEffect());
        final WeightedList<AbstractCard> possibleCards = GameUtilities.GetCardsInCombatWeighted(GenericCondition.FromT1(c -> GameUtilities.HasAffinity(c, Affinity.Violet) && !GameUtilities.HasAffinity(c, Affinity.Star)));

        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (int i=0; i<3; i++) {
            group.addToBottom(possibleCards.Retrieve(rng, true));
        }

        GameActions.Bottom.SelectFromPile(name, 1, group)
            .SetOptions(false, false)
            .AddCallback(cards ->
            {
                for (AbstractCard card : cards) {
                    AbstractCard cardToAdd = card.makeCopy();

                    if (upgraded) {
                        cardToAdd.upgrade();
                    }

                    GameEffects.TopLevelQueue.ShowAndObtain(cardToAdd);
                }
            });
    }
}