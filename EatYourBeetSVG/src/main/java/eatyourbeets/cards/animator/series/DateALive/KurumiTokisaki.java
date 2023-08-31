package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KurumiTokisaki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KurumiTokisaki.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public KurumiTokisaki()
    {
        super(DATA);

        Initialize(12, 12, 3);
        SetUpgrade(0,0,0);

        SetAffinity_Black(1);
        SetAffinity_Violet(1);
        SetAffinity_Green(1);

        SetEthereal(true);
        SetAutoplayed(true);
        
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.SFX("ATTACK_HEAVY");
        GameActions.Bottom.VFX(new DieDieDieEffect());
        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.NONE);

        GameActions.Bottom.GainBlur(magicNumber);

        GameActions.Bottom.DrawNextTurn(magicNumber);

        if (upgraded) {
            GameActions.Bottom.GainEnergyNextTurn(magicNumber);
        }
    }
}