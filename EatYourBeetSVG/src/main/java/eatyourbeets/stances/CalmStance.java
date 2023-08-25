package eatyourbeets.stances;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.stance.StanceAura;
import eatyourbeets.effects.stance.StanceParticleHorizontal;
import eatyourbeets.effects.stance.StanceParticleVertical;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class CalmStance extends EYBStance
{
    public static final String STANCE_ID = CreateFullID(CalmStance.class);

    public static boolean IsActive()
    {
        return GameUtilities.InStance(STANCE_ID);
    }
    public static final int ENERGY_GAIN_AMOUNT = 2;

    public CalmStance()
    {
        super(STANCE_ID, AbstractDungeon.player);
    }

    protected Color GetParticleColor()
    {
        return CreateColor(0.5f, 0.55f, 0.6f, 0.7f, 1.0F, 1.0F);
    }

    protected Color GetAuraColor()
    {
        return Color.SKY;
    }

    @Override
    public void onEnterStance()
    {
        super.onEnterStance();

        if (sfxId != -1L) {
            this.stopIdleSfx();
        }

        CardCrawlGame.sound.play(SFX.STANCE_ENTER_CALM);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.NAVY, true));
    }

    @Override
    public void onExitStance()
    {
        super.onExitStance();

        GameActions.Top.GainEnergy(ENERGY_GAIN_AMOUNT);

        this.stopIdleSfx();
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
        return new Color(0.5f, 0.7f, 1f, 1f);
    }
}
