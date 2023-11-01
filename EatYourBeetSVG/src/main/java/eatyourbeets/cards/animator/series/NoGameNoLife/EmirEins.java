package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class EmirEins extends AnimatorCard
{
    public static final EYBCardData DATA = Register(EmirEins.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.Normal)
            
            .SetSeriesFromClassPackage();

    public EmirEins()
    {
        super(DATA);

        Initialize(6, 0, 2);
        SetUpgrade(3, 0, 1);

        SetAffinity_Teal(1, 0, 1);
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        GameActions.Bottom.IncreaseScaling(this, Affinity.Violet, 2);
        GameActions.Bottom.IncreaseScaling(this, Affinity.Red, 2);
        GameActions.Bottom.Callback(() -> {
            Refresh(null);
        });
        GameActions.Bottom.ShowCopy(this);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);

        RandomizedList<AbstractCard> choices = new RandomizedList<>();

        CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        choices.AddAll(player.drawPile.getCardsOfType(CardType.ATTACK).group);

        for (int i=0; i<magicNumber; i++) {
            if (choices.Size() > 0) {
                cardGroup.addToBottom(choices.Retrieve(rng, true));
            }
        }

        GameActions.Bottom.SelectFromPile(name, 1, cardGroup)
            .SetFilter(card -> card.type == CardType.ATTACK)
            .SetOptions(false, false, false)
            .AddCallback(cards -> {
               for (AbstractCard card : cards) {
                   GameActions.Top.MakeCardInHand(GameUtilities.Imitate(card));
               }
            });
    }
}