package eatyourbeets.cards.animator.series.Elsword;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Aisha extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Aisha.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental)
            
            .SetSeries(CardSeries.Elsword);

    public Aisha()
    {
        super(DATA);

        Initialize(1, 0, 2, 3);
        SetUpgrade(2, 0, 0, 0);

        SetAffinity_Red(1);
        SetAffinity_Green(1);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        boolean redThresholdReached = GameUtilities.GetPowerAmount(Affinity.Red) >= secondaryValue;
        boolean greenThresholdReached = GameUtilities.GetPowerAmount(Affinity.Red) >= secondaryValue;

        GameActions.Bottom.SetScaling(this, Affinity.Red, redThresholdReached ? 1 : 0);
        GameActions.Bottom.SetScaling(this, Affinity.Green, greenThresholdReached ? 1 : 0);
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
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE).SetVFX(true, false)
            .SetDamageEffect(enemy ->
            {
                GameEffects.List.Add(VFX.SmallLaser(player.hb, enemy.hb, Color.PURPLE));
                return GameEffects.List.Add(VFX.SmallLaser(player.hb, enemy.hb, Color.VIOLET)).duration * 0.1f;
            });
        }
    }
}