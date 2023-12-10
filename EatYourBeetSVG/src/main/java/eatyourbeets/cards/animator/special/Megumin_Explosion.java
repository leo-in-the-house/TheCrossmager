package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Konosuba.Megumin;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;

import java.util.UUID;

public class Megumin_Explosion extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Megumin_Explosion.class)
            .SetAttack(3, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeries(Megumin.DATA.Series);

    public Megumin_Explosion()
    {
        super(DATA);

        Initialize(11, 0);
        SetUpgrade(4, 0);

        SetAffinity_Red(2, 0, 2);
        SetAffinity_Blue(2, 0, 2);

        SetUnique(true, true);
        SetExhaust(true);
    }

    private UUID linkedUUID;

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        if (timesUpgraded > 3)
        {
            LoadImage("_Alt");
        }
    }

    public void setLinkedUUID(UUID linkedUUID) {
        this.linkedUUID = linkedUUID;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.Wait(0.8f);

        if (linkedUUID != null && CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.ModifyAllInstances(linkedUUID, AbstractCard::upgrade)
                    .IncludeMasterDeck(true)
                    .IsCancellable(false);
            GameActions.Bottom.SFX(SFX.CARD_UPGRADE, 0.5f, 0.6f).SetDuration(0.25f, true);
        }

        GameActions.Bottom.WaitRealtime(0.2f);
        GameActions.Bottom.SFX(SFX.ORB_LIGHTNING_PASSIVE, 0.7f, 0.9f, 1.2f);
        GameActions.Bottom.WaitRealtime(0.5f);
        GameActions.Bottom.SFX(SFX.ORB_LIGHTNING_PASSIVE, 1.1f, 1.3f, 1.2f);
        GameActions.Bottom.BorderFlash(Color.ORANGE);
        GameActions.Bottom.WaitRealtime(0.4f);
        GameActions.Bottom.SFX(SFX.ORB_LIGHTNING_PASSIVE, 0.7f, 0.9f, 1.2f);
        GameActions.Bottom.WaitRealtime(0.4f);
        GameActions.Bottom.BorderFlash(Color.RED);
        GameActions.Bottom.SFX(SFX.ORB_LIGHTNING_PASSIVE, 1.1f, 1.3f, 1.2f);
        GameActions.Bottom.Callback(() ->
        {
            for (AbstractCreature m1 : GameUtilities.GetEnemies(true))
            {
                GameEffects.List.Attack(player, m1, AttackEffects.LIGHTNING, 0.7f, 0.8f, Color.RED);
                GameEffects.List.Add(VFX.FlameBarrier(m1.hb));
                for (int i = 0; i < 12; i++)
                {
                    GameEffects.List.Add(VFX.SmallExplosion(m1.hb, 0.4f).PlaySFX(i == 0));
                }
            }
        });
        GameActions.Bottom.WaitRealtime(0.35f);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE).SetVFX(true, true);
    }
}