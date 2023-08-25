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
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class MagicStance extends EYBStance
{
    public static final String STANCE_ID = CreateFullID(MagicStance.class);

    public static boolean IsActive()
    {
        return GameUtilities.InStance(STANCE_ID);
    }
    public static final int STAT_CHANGE_AMOUNT = 5;

    public MagicStance()
    {
        super(STANCE_ID, AbstractDungeon.player);
    }

    protected Color GetParticleColor()
    {
        return CreateColor(0.1f, 0.3f, 0.1f, 0.1f, 1.0F, 1.0F);
    }

    protected Color GetAuraColor()
    {
        return Color.NAVY;
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
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.NAVY, true));

        if (TryApplyStance(STANCE_ID))
        {
            GameUtilities.ApplyPowerInstantly(owner, PowerHelper.Focus, +STAT_CHANGE_AMOUNT);
            GameUtilities.ApplyPowerInstantly(owner, PowerHelper.Strength, -STAT_CHANGE_AMOUNT);
            GameUtilities.ApplyPowerInstantly(owner, PowerHelper.Dexterity, -STAT_CHANGE_AMOUNT);
        }
    }

    @Override
    public void onExitStance()
    {
        super.onExitStance();

        if (TryApplyStance(null))
        {
            GameUtilities.ApplyPowerInstantly(owner, PowerHelper.Focus, -STAT_CHANGE_AMOUNT);
            GameUtilities.ApplyPowerInstantly(owner, PowerHelper.Strength, +STAT_CHANGE_AMOUNT);
            GameUtilities.ApplyPowerInstantly(owner, PowerHelper.Dexterity, +STAT_CHANGE_AMOUNT);
        }

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
        return new Color(0.1f, 0.2f, 2f, 1f);
    }
    @Override
    public void updateDescription()
    {
        description = FormatDescription(STAT_CHANGE_AMOUNT);
    }
}
