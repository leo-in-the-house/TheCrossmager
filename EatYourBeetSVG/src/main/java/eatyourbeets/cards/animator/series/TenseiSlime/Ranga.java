package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnEvokeOrbSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ranga extends AnimatorCard implements OnEvokeOrbSubscriber
{
    public static final EYBCardData DATA = Register(Ranga.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental)
            .SetSeriesFromClassPackage();

    public Ranga()
    {
        super(DATA);

        Initialize(6, 0, 1);
        SetUpgrade(1, 0, 0);

        SetAffinity_Yellow(1, 0, 1);
        SetAffinity_Black(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetAttackTarget(EYBCardTarget.ALL);
        SetMultiDamage(true);
        upgradedDamage = true;
    }

    @Override
    public void OnEvokeOrb(AbstractOrb orb)
    {
        if (player.exhaustPile.contains(this))
        {
            GameActions.Last.MoveCard(this, player.exhaustPile, player.hand)
            .AddCallback(c -> {
                GameUtilities.IncreaseMagicNumber(this, 1, false);
                CostModifiers.For(this).Add(1);
            });
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            if (upgraded)
            {
                GameActions.Bottom.DealDamageToAll(this, AttackEffects.LIGHTNING);
            }
            else
            {
                GameActions.Bottom.DealDamage(this, m, AttackEffects.LIGHTNING);
            }
        }
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onEvokeOrb.Subscribe(this);
    }
}