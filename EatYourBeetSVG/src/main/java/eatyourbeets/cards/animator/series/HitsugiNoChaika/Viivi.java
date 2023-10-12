package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import eatyourbeets.effects.AttackEffects;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;
import eatyourbeets.cards.animator.special.ThrowingKnife;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;

public class Viivi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Viivi.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.Random)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                for (ThrowingKnife knife : ThrowingKnife.GetAllCards())
                {
                    data.AddPreview(knife, false);
                }
            });

    public Viivi()
    {
        super(DATA);

        Initialize(3, 0, 3, 2);
        SetUpgrade(1, 0, 0, 1);

        SetAffinity_Green(1, 0, 1);
    }

    @Override
    protected void OnUpgrade()
    {
        AddScaling(Affinity.Green, 1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.VFX(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()), 0f);
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.NONE);
        }
        GameUtilities.PlayVoiceSFX(name);

        if (info.TryActivateStarter())
        {
            GameActions.Bottom.CreateThrowingKnives(secondaryValue);
            GameActions.Bottom.Cycle(name, 1)
                    .SetFilter(ThrowingKnife.DATA::IsCard);
        }
    }
}