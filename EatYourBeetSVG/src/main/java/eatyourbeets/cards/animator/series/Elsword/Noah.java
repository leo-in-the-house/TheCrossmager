package eatyourbeets.cards.animator.series.Elsword;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Noah extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Noah.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Piercing)
            .SetSeriesFromClassPackage();

    protected Affinity curAffinity;

    public Noah()
    {
        super(DATA);

        Initialize(10, 0, 1, 7);
        SetUpgrade(0, 0, 0);

        SetAffinity_Blue(1, 0, 3);
        SetAffinity_Black(1);

        curAffinity = Affinity.Blue;
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetLoyal(true);
        AddScaling(curAffinity, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HORIZONTAL)
        .SetDamageEffect(c -> GameEffects.List.Add(VFX.Clash(c.hb)).SetColors(Color.LIGHT_GRAY, Color.RED, Color.GREEN, Color.BLUE).duration * 0.6f)
        .AddCallback(enemy -> {
            Affinity oldCurAffinity = curAffinity;

            if (curAffinity == Affinity.Red) {
                curAffinity = Affinity.Green;
            }
            else if (curAffinity == Affinity.Blue) {
                curAffinity = Affinity.Red;
            }
            else if (curAffinity == Affinity.Green) {
                curAffinity = Affinity.Blue;
            }

            TransitionAffinity(oldCurAffinity, curAffinity);
        });

    }

    @Override
    public void AfterAffinitiesRestored()
    {
        if (curAffinity != Affinity.Blue) {
            TransitionAffinity(Affinity.Blue, curAffinity);
        }
    }

    private void TransitionAffinity(Affinity oldAffinity, Affinity newAffinity) {
        int curAffinityAmount = affinities.GetLevel(oldAffinity);
        int curScaling = affinities.GetScaling(oldAffinity, false);

        if (curAffinityAmount > 1) {
            affinities.sealed = false;
        }

        SetScaling(oldAffinity, 0);
        affinities.Set(oldAffinity, 0);

        AddScaling(newAffinity, curScaling);
        affinities.Set(newAffinity, curAffinityAmount);
    }
}