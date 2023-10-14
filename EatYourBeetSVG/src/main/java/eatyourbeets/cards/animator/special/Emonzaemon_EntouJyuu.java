package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Katanagatari.Emonzaemon;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnAffinitySealedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Emonzaemon_EntouJyuu extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Emonzaemon_EntouJyuu.class)
            .SetPower(2, CardRarity.SPECIAL)
            .SetSeries(Emonzaemon.DATA.Series);

    public Emonzaemon_EntouJyuu()
    {
        super(DATA);

        Initialize(0, 0, 10);
        SetUpgrade(0, 0, 5);

        SetAffinity_Red(1);
        SetAffinity_Teal(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        CombatStats.Affinities.AddAffinitySealUses(1);
        GameActions.Bottom.StackPower(new Emonzaemon_EntouJyuuPower(p, magicNumber));
    }

    public class Emonzaemon_EntouJyuuPower extends AnimatorPower implements OnAffinitySealedSubscriber
    {
        public Emonzaemon_EntouJyuuPower(AbstractCreature owner, int amount)
        {
            super(owner, Emonzaemon_EntouJyuu.DATA);

            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onAffinitySealed.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onAffinitySealed.Unsubscribe(this);
        }

        @Override
        public void OnAffinitySealed(EYBCard card, boolean manual)
        {
            AbstractMonster m = GameUtilities.GetRandomEnemy(true);
            if (m != null) {
                GameActions.Bottom.DealDamage(player, m, amount, DamageInfo.DamageType.THORNS, AttackEffects.GUNSHOT);
            }
        }

    }
}