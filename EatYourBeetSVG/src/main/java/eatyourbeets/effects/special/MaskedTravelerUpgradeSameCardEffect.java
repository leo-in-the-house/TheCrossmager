package eatyourbeets.effects.special;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class MaskedTravelerUpgradeSameCardEffect extends AbstractGameEffect
{
    private static final float DUR = 1.5f;
    private final Color screenColor;
    private final String message;
    private final int upgrades;
    private boolean openedPanel = false;

    public MaskedTravelerUpgradeSameCardEffect(int upgrades)
    {
        this.message = CardCrawlGame.languagePack.getUIString("CampfireSmithEffect").TEXT[0];
        this.upgrades = upgrades;
        this.duration = 1.5f;
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.screenColor.a = 0f;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update()
    {
        if (!openedPanel)
        {
            OpenPanel();
        }
        else if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0)
        {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);

            GameEffects.Queue.Add(new UpgradeShineEffect((float)Settings.WIDTH / 2f, (float)Settings.HEIGHT / 2f));

            for (int i = 0; i < upgrades; i++)
            {
                if (c.canUpgrade())
                {
                    c.upgrade();
                }
            }

            GameEffects.Queue.ShowCardBriefly(c.makeStatEquivalentCopy());
            GameUtilities.GetCurrentRoom(true).phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();

            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0f, 0f, (float) Settings.WIDTH, (float) Settings.HEIGHT);
        if (AbstractDungeon.screen == CurrentScreen.GRID)
        {
            AbstractDungeon.gridSelectScreen.render(sb);
        }
    }

    public void dispose()
    {
    }

    public void OpenPanel()
    {
        CardGroup upgradableCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group)
        {
            if (c.canUpgrade())
            {
                upgradableCards.group.add(c);
            }
        }

        if (upgradableCards.isEmpty())
        {
            this.isDone = true;
            return;
        }

        if (AbstractDungeon.isScreenUp)
        {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        AbstractDungeon.gridSelectScreen.open(upgradableCards, 1, message, false, false, false, false);
        openedPanel = true;
    }
}
