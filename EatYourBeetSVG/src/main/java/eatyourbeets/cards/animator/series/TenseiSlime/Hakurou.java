package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Hakurou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Hakurou.class)
            .SetAttack(3, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public Hakurou()
    {
        super(DATA);

        Initialize(2, 0, 8);
        SetUpgrade(0, 0, 6);

        SetAffinity_Green(2);

        SetAffinityRequirement(Affinity.Green, 3);
    }


    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {

            AbstractGameAction.AttackEffect attackEffect = AttackEffects.PUNCH;

            if (i%4 == 0) {
                attackEffect = AttackEffects.SLASH_VERTICAL;
            }
            if (i%4 == 1) {
                attackEffect = AttackEffects.SLASH_HORIZONTAL;
            }
            if (i%4 == 2) {
                attackEffect = AttackEffects.SPEAR;
            }
            GameActions.Bottom.DealDamage(this, m, attackEffect);
        }

        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
        }
    }
}