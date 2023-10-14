package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnAfterCardDiscardedSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class ChaikaTrabant extends AnimatorCard implements OnAfterCardDiscardedSubscriber
{
    public static final EYBCardData DATA = Register(ChaikaTrabant.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public ChaikaTrabant()
    {
        super(DATA);

        Initialize(23, 0, 0);
        SetUpgrade(6, 0, 0);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Teal(1, 0, 1);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        AddScaling(Affinity.Blue, 1);
        AddScaling(Affinity.Teal, 1);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onAfterCardDiscarded.Subscribe(this);
    }

    @Override
    public void OnAfterCardDiscarded()
    {
        if (player.hand.contains(this)) {
            for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                if (enemy != null) {
                    if (rng.randomBoolean()) {
                        GameActions.Top.ApplyWeak(TargetHelper.Normal(enemy), 1);
                    }
                    else {
                        GameActions.Top.ApplyVulnerable(TargetHelper.Normal(enemy), 1);
                    }
                }
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        ChaikaTrabant other = (ChaikaTrabant) makeStatEquivalentCopy();
        other.tags.remove(GR.Enums.CardTags.IGNORE_PEN_NIB);
        CombatStats.onStartOfTurnPostDraw.Subscribe(other);
    }

    @Override
    public void OnStartOfTurnPostDraw()
    {
        GameEffects.Queue.ShowCardBriefly(makeStatEquivalentCopy());

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.FIRE);
        GameUtilities.RemoveDamagePowers();
        CombatStats.onStartOfTurnPostDraw.Unsubscribe(this);
    }
}