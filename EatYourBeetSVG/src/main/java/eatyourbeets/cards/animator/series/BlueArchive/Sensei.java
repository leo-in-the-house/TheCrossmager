package eatyourbeets.cards.animator.series.BlueArchive;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.CombatStats;
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
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        if (CombatStats.TryActivateLimited(cardID)) {
            TryAddStudentToMasterDeck();
            DrawAbydosStudents();
        }
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

        for (AbstractCard card : obtainedStudents.values()) {
            possibleStudents.Add(card);
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

        boolean searchedExhaustPile = false;
        boolean searchedDiscardPile = false;

        HashMap<String, AnimatorCard> abydosStudents = GameUtilities.GetAbydosStudents();

        for (int i=0; i<numStudentsMax; i++) {
            if (player.hand.size() >= BaseMod.MAX_HAND_SIZE) {
                break;
            }

            //Sensei draws from the exhaust pile > discard pile > draw pile
            AbstractCard student = null;

            if (!searchedExhaustPile) {
                student = FindStudent(player.exhaustPile, abydosStudents);

                if (student == null) {
                    searchedExhaustPile = true;
                }
            }

            if (!searchedDiscardPile) {
                student = FindStudent(player.discardPile, abydosStudents);

                if (student == null) {
                    searchedDiscardPile = true;
                }
            }

            student = FindStudent(player.drawPile, abydosStudents);

            if (student == null) {
                break;
            }

            GameActions.Bottom.Draw(student);
        }
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