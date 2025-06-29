package eatyourbeets.cards.animatorClassic.series.FullmetalAlchemist;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.PhilosopherStone;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;

public class Father extends AnimatorClassicCard
{
    private static final AbstractRelic relic = new PhilosopherStone();
    private static final EYBCardTooltip tooltip = new EYBCardTooltip(relic.name, relic.description);

    public static final EYBCardData DATA = Register(Father.class).SetSeriesFromClassPackage()
            .SetSkill(4, CardRarity.RARE, EYBCardTarget.None)

            .SetSeriesFromClassPackage();

    public Father()
    {
        super(DATA);

        Initialize(0, 0, 3, 50);
        SetCostUpgrade(-1);

        SetExhaust(true);
        SetObtainableInCombat(false);
    }

    @Override
    public void initializeDescription()
    {
        super.initializeDescription();

        if (cardText != null)
        {
            tooltip.SetIcon(relic);
            tooltip.id = cardID + ":" + tooltip.title;
            tooltips.add(tooltip);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        if (!p.hasRelic(relic.relicId))
        {
            p.decreaseMaxHealth((int)Math.ceil(p.maxHealth * (secondaryValue / 100f)));
            GameActions.Bottom.VFX(new OfferingEffect(), 0.5f);
            GameActions.Bottom.Callback(() -> GameEffects.Queue.SpawnRelic(relic.makeCopy(), current_x, current_y));
            AbstractDungeon.bossRelicPool.remove(relic.relicId);

            p.energy.energy += 1;
        }
        else
        {
            GameActions.Bottom.GainVitality(magicNumber);
        }
    }
}