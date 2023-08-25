package eatyourbeets.stances;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.stance.StanceAura;
import eatyourbeets.effects.stance.StanceParticleHorizontal;
import eatyourbeets.effects.stance.StanceParticleVertical;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class DivinityStance extends EYBStance
{
    public static final String STANCE_ID = CreateFullID(DivinityStance.class);

    public static boolean IsActive()
    {
        return GameUtilities.InStance(STANCE_ID);
    }
    public static final int ENERGY_GAIN_AMOUNT = 3;

    public DivinityStance()
    {
        super(STANCE_ID, AbstractDungeon.player);
    }

    protected Color GetParticleColor()
    {
        return CreateColor(0.6f, 0.7f, 0.6F, 0.7F, 0.0f, 0.1f);
    }

    protected Color GetAuraColor()
    {
        return Color.GOLD;
    }

    @Override
    public void onEnterStance()
    {
        super.onEnterStance();

        if (sfxId != -1L) {
            this.stopIdleSfx();
        }

        CardCrawlGame.sound.play(SFX.STANCE_ENTER_DIVINITY);
        sfxId = CardCrawlGame.sound.playAndLoop(SFX.STANCE_LOOP_DIVINITY);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));

        if (TryApplyStance(STANCE_ID))
        {
            GameActions.Top.GainEnergy(ENERGY_GAIN_AMOUNT);
        }
    }

    @Override
    public void onExitStance()
    {
        super.onExitStance();

        this.stopIdleSfx();
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 3.0F : damage;
    }

    public void atStartOfTurn() {
        GameActions.Top.ChangeStance(NeutralStance.STANCE_ID);
    }

    @Override
    public void onRefreshStance()
    {
        //GameActions.Bottom.StackAffinityPower(AFFINITY, 1, true);
    }

    @Override
    protected void QueueParticle()
    {
        GameEffects.Queue.Add(new StanceParticleHorizontal(GetParticleColor()));
        GameEffects.Queue.Add(new StanceParticleVertical(GetAuraColor()));
    }

    @Override
    protected void QueueAura()
    {
        if (Settings.DISABLE_EFFECTS)
        {
            GameEffects.Queue.Add(new StanceAura(GetAuraColor()));
        }
    }

    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop(SFX.STANCE_LOOP_CALM, sfxId);
            sfxId = -1L;
        }

    }

    @Override
    protected Color GetMainColor()
    {
        return new Color(1f, 1f, 0.1f, 1f);
    }
}
