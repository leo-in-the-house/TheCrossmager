package eatyourbeets.cards.animator.colorless.uncommon;

import eatyourbeets.effects.AttackEffects;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;

public class Urushihara extends AnimatorCard implements OnStartOfTurnPostDrawSubscriber
{
    public static final EYBCardData DATA = Register(Urushihara.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.HatarakuMaouSama);

    private int turns = 0;

    public Urushihara()
    {
        super(DATA);

        Initialize(24, 0, 2, 5);
        SetUpgrade(4, 0, -1, -1);

        SetAffinity_Black(1);
        SetAffinity_Violet(1);

        SetEvokeOrbCount(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ChannelOrb(new Dark());

        Urushihara other = (Urushihara) makeStatEquivalentCopy();
        other.turns = rng.random(magicNumber, secondaryValue);
        CombatStats.onStartOfTurnPostDraw.Subscribe(other);
    }

    @Override
    public void OnStartOfTurnPostDraw()
    {
        if (turns > 0)
        {
            turns -= 1;
        }
        else
        {
            applyPowers();

            GameEffects.Queue.ShowCardBriefly(this);

            GameActions.Bottom.DealDamageToAll(this, AttackEffects.FIRE);
            GameUtilities.RemoveDamagePowers();

            CombatStats.onStartOfTurnPostDraw.Unsubscribe(this);
        }
    }
}