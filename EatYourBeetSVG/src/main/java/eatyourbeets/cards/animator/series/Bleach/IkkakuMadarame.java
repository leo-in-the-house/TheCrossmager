package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.animator.special.IkkakuBankai;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;

public class IkkakuMadarame extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IkkakuMadarame.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Piercing, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new IkkakuBankai(), false);
    }

    public IkkakuMadarame()
    {
        super(DATA);

        Initialize(23, 0, 0);
        SetUpgrade(12, 0, 0);

        SetAffinity_Red(1);

    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        if (WrathStance.IsActive())
        {
            GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID);
            GameActions.Bottom.MakeCardInDrawPile(new IkkakuBankai());
            GameActions.Last.ModifyAllInstances(uuid).AddCallback(GameActions.Bottom::Exhaust);
        }
    }
}