package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.HashMap;

public class Arona extends AnimatorCard {
    public static final EYBCardData DATA = Register(Arona.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.BlueArchive);

    public Arona() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainTeal(magicNumber);

        HashMap<String, AnimatorCard> abydosStudents = GameUtilities.GetAbydosStudents();

        for (AbstractCard card : player.hand.group) {
            boolean marked = false;

            if (upgraded && GameUtilities.IsSeries(card, CardSeries.BlueArchive)) {
                marked = true;
            }
            else if (!upgraded) {
                if (abydosStudents.get(card.cardID) != null)
                {
                    marked = true;
                }
            }

            if (marked) {
                GameActions.Top.IncreaseScaling(card, Affinity.Teal, 1);
            }
        }
    }
}