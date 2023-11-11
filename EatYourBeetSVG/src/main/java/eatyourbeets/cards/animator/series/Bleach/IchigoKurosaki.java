package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.animator.special.IchigoBankai;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;

public class IchigoKurosaki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IchigoKurosaki.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new IchigoBankai(), true);
    }

    public IchigoKurosaki()
    {
        super(DATA);

        Initialize(12, 0, 2, 0);
        SetUpgrade(3, 0, 1, 0);

        SetAffinity_White(2, 0, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToRandomEnemy(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        GameActions.Bottom.GainWhite(magicNumber);

        if (WrathStance.IsActive())
        {
            GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID);
            GameActions.Bottom.MakeCardInDrawPile(new IchigoBankai())
                    .SetUpgrade(upgraded, true);
            GameActions.Last.ModifyAllInstances(uuid).AddCallback(GameActions.Bottom::Exhaust);
        }
    }
}