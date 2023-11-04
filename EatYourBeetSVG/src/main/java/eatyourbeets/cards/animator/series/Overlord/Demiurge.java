package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Demiurge extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Demiurge.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Demiurge()
    {
        super(DATA);

        Initialize(0,0,3, 2);
        SetUpgrade(0,0,-1, 1);

        SetAffinity_Red(1);

        SetRetain(true);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.TakeDamageAtEndOfTurn(magicNumber, AttackEffects.DARK)
             .AddCallback(power -> {
                 GameActions.Top.ApplyBurning(TargetHelper.Enemies(), secondaryValue);
             });
    }
}