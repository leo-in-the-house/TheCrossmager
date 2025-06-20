package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

import java.util.HashMap;

public class Sensei extends AnimatorCard {
    public static final EYBCardData DATA = Register(Sensei.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Sensei() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Star(1);

        SetExhaust(true);
        SetRetain(true);

        SetHealing(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        TryAddStudentToMasterDeck();
        DrawAbydosStudents();
    }

    private void TryAddStudentToMasterDeck() {
        HashMap<String, AnimatorCard> abydosStudents = GameUtilities.GetAbydosStudents();
        HashMap<String, AbstractCard> obtainedStudents = new HashMap<>();
        RandomizedList<AbstractCard> possibleStudents = new RandomizedList<>();

        for (AbstractCard card : player.masterDeck.group) {
            if (abydosStudents.get(card.cardID) != null && obtainedStudents.get(card.cardID) == null) {
                obtainedStudents.put(card.cardID, card.makeCopy());
            }
        }

        for (AbstractCard card : abydosStudents.values()) {
            if (obtainedStudents.get(card.cardID) == null) {
                possibleStudents.Add(card);
            }
        }

        if (possibleStudents.Size() > 0) {
            GameEffects.TopLevelQueue.ShowAndObtain(possibleStudents.Retrieve(rng).makeCopy());
        }
    }

    private void DrawAbydosStudents() {
        int numEnemiesLockOn = 0;

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            if (enemy.hasPower(LockOnPower.POWER_ID)) {
                numEnemiesLockOn++;
            }
        }

        int numStudentsMax = 2 * numEnemiesLockOn;

        HashMap<String, AnimatorCard> abydosStudents = GameUtilities.GetAbydosStudents();

        GameActions.Bottom.FetchFromPile(name, numStudentsMax, player.exhaustPile, player.discardPile, player.drawPile)
                .ShowEffect(true, true)
                .SetFilter(card -> abydosStudents.get(card.cardID) != null)
                .SetOptions(true, true);
    }

    private AbstractCard FindStudent(CardGroup group, HashMap<String, AnimatorCard> abydosStudents) {

        for (AbstractCard card : group.group) {
            if (abydosStudents.get(card.cardID) != null) {
                return card;
            }
        }

        return null;
    }
}