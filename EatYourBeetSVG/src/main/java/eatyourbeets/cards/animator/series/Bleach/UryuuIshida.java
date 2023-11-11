package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class UryuuIshida extends AnimatorCard
{
    public static final EYBCardData DATA = Register(UryuuIshida.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage();

    public UryuuIshida()
    {
        super(DATA);

        Initialize(7, 0, 0, 0);
        SetUpgrade(2, 0, 0);

        SetAffinity_Blue(1, 0, 2);
        SetAffinity_Teal(1, 0, 2);

    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        AddScaling(Affinity.Blue, 1);
        AddScaling(Affinity.Teal, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT)
                .SetSoundPitch(1.2f, 1.5f);

        TransferWeakVulnerable(m);
    }

    private void TransferWeakVulnerable(AbstractMonster m)
    {
        int weakToTransfer = GameUtilities.GetPowerAmount(player, WeakPower.POWER_ID);
        int vulToTransfer = GameUtilities.GetPowerAmount(player, VulnerablePower.POWER_ID);

        for (AbstractPower power : player.powers)
        {
            if (WeakPower.POWER_ID.equals(power.ID) && weakToTransfer > 0)
            {
                GameActions.Bottom.ReducePower(power, weakToTransfer);
                GameActions.Bottom.ApplyWeak(player, m, weakToTransfer);
            }
            else if (VulnerablePower.POWER_ID.equals(power.ID) && vulToTransfer > 0)
            {
                GameActions.Bottom.ReducePower(power, vulToTransfer);
                GameActions.Bottom.ApplyVulnerable(player, m, vulToTransfer);
            }
        }
    }
}