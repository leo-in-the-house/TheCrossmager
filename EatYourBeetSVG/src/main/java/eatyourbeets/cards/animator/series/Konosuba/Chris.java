package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Chris extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Chris.class)
            .SetAttack(0, CardRarity.COMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Konosuba_Eris(401, Eris.DATA));
                data.AddPreview(new Eris(), false);
            });;

    public Chris()
    {
        super(DATA);

        Initialize(3, 0, 3, 0);
        SetUpgrade(3, 0, 0, 0);

        SetAffinity_Yellow(1);
        SetObtainableInCombat(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.DAGGER);
        GameActions.Bottom.GainGold(magicNumber);
    }
}