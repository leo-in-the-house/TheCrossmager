package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RangikuMatsumoto extends AnimatorCard implements OnStanceChangedSubscriber {
    public static final EYBCardData DATA = Register(RangikuMatsumoto.class)
            .SetAttack(0, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();

    public RangikuMatsumoto() {
        super(DATA);

        Initialize(5, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Black(1);
    }

    @Override
    public void OnStanceChanged(AbstractStance oldStance, AbstractStance newStance) {
        if (GameUtilities.InGame() && !GameUtilities.InStance(NeutralStance.STANCE_ID)) {
            SetAttackTarget(EYBCardTarget.ALL);
        }
        else {
            SetAttackTarget(EYBCardTarget.Random);
        }
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        if (GameUtilities.InGame() && !GameUtilities.InStance(NeutralStance.STANCE_ID)) {
            SetAttackTarget(EYBCardTarget.ALL);
            SetMultiDamage(true);
        }
        else {
            SetAttackTarget(EYBCardTarget.Random);
            SetMultiDamage(false);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        if (!GameUtilities.InStance(NeutralStance.STANCE_ID)) {
            GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
        else {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }

    }
}