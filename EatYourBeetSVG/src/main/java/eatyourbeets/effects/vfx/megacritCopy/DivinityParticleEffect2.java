package eatyourbeets.effects.vfx.megacritCopy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import eatyourbeets.effects.EYBEffect;

public class DivinityParticleEffect2 extends EYBEffect
{
    private float x;
    private float y;
    private float vY;
    private float dur_div2;
    private TextureAtlas.AtlasRegion img;

    public DivinityParticleEffect2(AbstractCreature target)
    {
        this.scale = Settings.scale;
        this.img = ImageMaster.EYE_ANIM_0;
        this.scale = MathUtils.random(1.0F, 1.5F);
        this.startingDuration = this.scale + 0.8F;
        this.duration = this.startingDuration;
        this.scale *= Settings.scale;
        this.dur_div2 = this.duration / 2.0F;
        this.color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.5F, 0.7F), MathUtils.random(0.8F, 1.0F), 0.0F);
        this.x = target.hb.cX + MathUtils.random(-target.hb.width / 2.0F - 50.0F * Settings.scale, target.hb.width / 2.0F + 50.0F * Settings.scale);
        this.y = target.hb.cY + MathUtils.random(-target.hb.height / 2.0F + 10.0F * Settings.scale, target.hb.height / 2.0F - 20.0F * Settings.scale);
        this.renderBehind = MathUtils.randomBoolean();
        this.rotation = MathUtils.random(12.0F, 6.0F);
        if (this.x > target.hb.cX)
        {
            this.rotation = -this.rotation;
        }

        this.x -= (float) this.img.packedWidth / 2.0F;
        this.y -= (float) this.img.packedHeight / 2.0F;
    }

    public DivinityParticleEffect2 SetColor(float r1, float r2, float g1, float g2, float b1, float b2)
    {
        this.color = new Color(Random(r1, r2), Random(g1, g2), Random(b1, b2), 0.0F);

        return this;
    }

    public DivinityParticleEffect2 SetColor(Color color)
    {
        this.color = color.cpy();

        return this;
    }

    public void update()
    {
        if (this.duration > this.dur_div2)
        {
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
        }
        else
        {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);
        }

        if (this.duration > this.startingDuration * 0.85F)
        {
            this.vY = 12.0F * Settings.scale;
            this.img = ImageMaster.EYE_ANIM_0;
        }
        else if (this.duration > this.startingDuration * 0.8F)
        {
            this.vY = 8.0F * Settings.scale;
            this.img = ImageMaster.EYE_ANIM_1;
        }
        else if (this.duration > this.startingDuration * 0.75F)
        {
            this.vY = 4.0F * Settings.scale;
            this.img = ImageMaster.EYE_ANIM_2;
        }
        else if (this.duration > this.startingDuration * 0.7F)
        {
            this.vY = 3.0F * Settings.scale;
            this.img = ImageMaster.EYE_ANIM_3;
        }
        else if (this.duration > this.startingDuration * 0.65F)
        {
            this.img = ImageMaster.EYE_ANIM_4;
        }
        else if (this.duration > this.startingDuration * 0.6F)
        {
            this.img = ImageMaster.EYE_ANIM_5;
        }
        else if (this.duration > this.startingDuration * 0.55F)
        {
            this.img = ImageMaster.EYE_ANIM_6;
        }
        else if (this.duration > this.startingDuration * 0.38F)
        {
            this.img = ImageMaster.EYE_ANIM_5;
        }
        else if (this.duration > this.startingDuration * 0.3F)
        {
            this.img = ImageMaster.EYE_ANIM_4;
        }
        else if (this.duration > this.startingDuration * 0.25F)
        {
            this.vY = 3.0F * Settings.scale;
            this.img = ImageMaster.EYE_ANIM_3;
        }
        else if (this.duration > this.startingDuration * 0.2F)
        {
            this.vY = 4.0F * Settings.scale;
            this.img = ImageMaster.EYE_ANIM_2;
        }
        else if (this.duration > this.startingDuration * 0.15F)
        {
            this.vY = 8.0F * Settings.scale;
            this.img = ImageMaster.EYE_ANIM_1;
        }
        else
        {
            this.vY = 12.0F * Settings.scale;
            this.img = ImageMaster.EYE_ANIM_0;
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F)
        {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y + this.vY, (float) this.img.packedWidth / 2.0F, (float) this.img.packedHeight / 2.0F, (float) this.img.packedWidth, (float) this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }
}
