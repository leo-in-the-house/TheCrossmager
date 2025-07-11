package eatyourbeets.effects.card;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.EYBEffect;

public class RenderCardEffect extends EYBEffect
{
    private final AbstractCard card;

    public RenderCardEffect(AbstractCard card, float duration, boolean isRealtime)
    {
        super(duration, isRealtime);

        this.card = card;

        card.untip();
        card.unhover();
        card.unfadeOut();
        card.lighten(true);
    }

    public void render(SpriteBatch sb)
    {
        card.render(sb);
    }

    public void dispose()
    {

    }
}