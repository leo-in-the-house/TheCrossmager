package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Chibimoth extends AnimatorCard {
    public static final EYBCardData DATA = Register(Chibimoth.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.Rewrite);

    private static final RandomizedList<Affinity> affinities = new RandomizedList<>();

    public Chibimoth() {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 3);

        SetExhaust(true);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (affinities.Size() == 0)
        {
            affinities.AddAll(Affinity.Basic());
        }

        InitializeAffinity(affinities.Retrieve(rng, false), 1, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID)
            .AddCallback(stance -> {
                if (stance != null && GameUtilities.InStance(NeutralStance.STANCE_ID) && !stance.ID.equals(NeutralStance.STANCE_ID)) {
                    GameActions.Top.GainTemporaryHP(magicNumber);
                }
            });
    }
}