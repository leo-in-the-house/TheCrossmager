package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Arona;
import eatyourbeets.cards.animator.special.Shiroko_Terror;
import eatyourbeets.cards.animator.ultrarare.YumeKuchinashi;
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
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Arona(), false);
                data.AddPreview(new ShirokoSunaookami(), false);
                data.AddPreview(new AyaneOkusora(), false);
                data.AddPreview(new SerikaKuromi(), false);
                data.AddPreview(new NonomiIzayoi(), false);
                data.AddPreview(new HoshinoTakanashi(), false);
            });

    public Sensei() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Star(2);

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

        //Cannot obtain these
        abydosStudents.remove(Shiroko_Terror.DATA.ID);
        abydosStudents.remove(YumeKuchinashi.DATA.ID);

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
            AbstractCard student = possibleStudents.Retrieve(rng);
            GameEffects.TopLevelQueue.ShowAndObtain(student.makeCopy());
            GameActions.Bottom.MakeCardInHand(student.makeCopy());
        }
    }

    private void DrawAbydosStudents() {

        int numStudentsMax = 2 * GameUtilities.GetEnemies(true).size();

        HashMap<String, AnimatorCard> abydosStudents = GameUtilities.GetAbydosStudents();

        GameActions.Bottom.FetchFromPile(name, numStudentsMax, player.exhaustPile, player.discardPile, player.drawPile)
                .ShowEffect(true, true)
                .SetFilter(card -> abydosStudents.get(card.cardID) != null)
                .SetOptions(true, true)
                .AddCallback(cards -> {
                    for (AbstractCard card : cards) {
                        GameActions.Top.Motivate(card, 1);
                    }
                });
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