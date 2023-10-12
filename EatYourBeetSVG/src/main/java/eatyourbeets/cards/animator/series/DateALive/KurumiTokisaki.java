package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
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

        SetDelayed(true);
        SetEthereal(true);
        SetAutoplayed(true);
        
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
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
        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.GUNSHOT);
        }

        GameActions.Bottom.GainBlur(magicNumber);

        GameActions.Bottom.DrawNextTurn(magicNumber);

        if (upgraded) {
            GameActions.Bottom.GainEnergyNextTurn(magicNumber);
        }
    }
}