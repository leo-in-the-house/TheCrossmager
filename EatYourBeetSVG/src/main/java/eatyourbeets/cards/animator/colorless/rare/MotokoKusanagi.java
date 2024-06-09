package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class MotokoKusanagi extends AnimatorCard implements OnStartOfTurnPostDrawSubscriber {
    public static final EYBCardData DATA = Register(MotokoKusanagi.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.Normal)
            .SetSeries(CardSeries.GhostInTheShell)
            .SetColor(CardColor.COLORLESS);

    public MotokoKusanagi() {
        super(DATA);

        Initialize(4, 0, 4);
        SetUpgrade(0, 0, 1);

        SetExhaust(true);
        SetDelayed(true);

        SetRicochet(5, 0, this::OnCooldownCompleted);

        SetAffinity_Teal(1, 0, 1);
        SetAffinity_Black(1, 0, 1);
    }

    @Override
    public void OnStartOfTurnPostDraw()
    {
        if (player.exhaustPile.contains(this))
        {
            GameEffects.List.ShowCopy(this, Settings.WIDTH * 0.75f, Settings.HEIGHT * 0.4f);

            cooldown.ProgressCooldownAndTrigger(null);
        }
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onStartOfTurnPostDraw.Subscribe(this);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.StackPower(new BufferPower(player, 1));
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);
        }
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.MoveCard(this, player.exhaustPile, player.hand)
                .ShowEffect(true, false);
    }
}