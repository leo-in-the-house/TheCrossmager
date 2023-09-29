package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Chim extends AnimatorCard {
    public static final EYBCardData DATA = Register(Chim.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.Atelier);

    public Chim() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(1);
        SetAffinity_Teal(1);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (!startOfBattle) {
            GameActions.Bottom.GainEnergy(1);
            GameActions.Bottom.Draw(2);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        RandomizedList<AbstractCard> possibleCards = GetPossibleTargetsForImitate();

        GameActions.Bottom.MakeCardInHand(GameUtilities.Imitate(possibleCards.Retrieve(rng)));
    }
    private RandomizedList<AbstractCard> GetPossibleTargetsForImitate() {
        RandomizedList<AbstractCard> possibleTargets = new RandomizedList<>();

        for (AbstractCard card : player.masterDeck.group) {
            if (!GameUtilities.IsHindrance(card)) {
                possibleTargets.Add(card);
            }
        }

        return possibleTargets;
    }

}