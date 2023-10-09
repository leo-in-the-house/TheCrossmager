package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.CreateRandomGoblins;
import eatyourbeets.cards.animator.status.GoblinChampion;
import eatyourbeets.cards.animator.status.GoblinKing;
import eatyourbeets.cards.animator.status.GoblinShaman;
import eatyourbeets.cards.animator.status.GoblinSoldier;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Fighter extends AnimatorCard {
    public static final EYBCardData DATA = Register(Fighter.class)
            .SetAttack(0, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new GoblinSoldier(), false);
                data.AddPreview(new GoblinShaman(), false);
                data.AddPreview(new GoblinChampion(), false);
                data.AddPreview(new GoblinKing(), false);
            });

    public Fighter() {
        super(DATA);

        Initialize(10, 0, 2);
        SetUpgrade(3, 0, 0);

        SetFading(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.MakeCardInDrawPile(CreateRandomGoblins.GetRandomGoblin(rng));
        }
    }
}