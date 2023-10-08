package eatyourbeets.stances;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.stance.StanceAura;
import eatyourbeets.effects.stance.StanceParticleHorizontal;
import eatyourbeets.effects.stance.StanceParticleVertical;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class WrathStance extends EYBStance
{
    public static final String STANCE_ID = CreateFullID(WrathStance.class);
    public static final String NAME = "Wrath Stance";

    public static boolean IsActive()
    {
        return GameUtilities.InStance(STANCE_ID);
    }

    public WrathStance()
    {
        super(STANCE_ID, AbstractDungeon.player);
    }

    protected Color GetParticleColor()
    {
        return CreateColor(1.0F, 1.0F, 0.1f, 0.3f, 0.1f, 0.1f);
    }

    protected Color GetAuraColor()
    {
        return Color.SCARLET;
    }

    @Override
    public void onEnterStance()
    {
        super.onEnterStance();

        if (sfxId != -1L) {
            this.stopIdleSfx();
        }

        CardCrawlGame.sound.play(SFX.STANCE_ENTER_WRATH);
        sfxId = CardCrawlGame.sound.playAndLoop(SFX.STANCE_LOOP_WRATH);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
    }

    @Override
    public void onExitStance()
    {
        super.onExitStance();

        this.stopIdleSfx();
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
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
            CardCrawlGame.sound.stop(SFX.STANCE_LOOP_WRATH, sfxId);
            sfxId = -1L;
        }

    }

    @Override
    protected Color GetMainColor()
    {
        return new Color(2f, 0.1f, 0.2f, 1f);
    }
}
