package eatyourbeets.relics.animator;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import eatyourbeets.actions.animator.CreateRandomCurses;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.relics.AnimatorRelic;
import eatyourbeets.utilities.JUtils;

public class CrumblingOrb extends AnimatorRelic
{
    public static final String ID = CreateFullID(CrumblingOrb.class);
    public static final int CHOOSE_AMOUNT = 2;
    public static final int CHOOSE_SIZE = 4;

    private Random battleRNG;

    public CrumblingOrb()
    {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription()
    {
        return FormatDescription(0, CHOOSE_AMOUNT, CHOOSE_SIZE);
    }

    @Override
    public void onEquip()
    {
        super.onEquip();

        player.energy.energyMaster += 1;
    }

    @Override
    public void onUnequip()
    {
        super.onUnequip();

        player.energy.energyMaster -= 1;
    }

    @Override
    public void atBattleStartPreDraw()
    {
        super.atBattleStartPreDraw();

        battleRNG = new Random(rng.randomLong(), 0);
    }

    @Override
    public void onVictory()
    {
        super.onVictory();

        battleRNG = null;
    }

    @Override
    public void atTurnStart()
    {
        super.atTurnStart();

        if (battleRNG == null)
        {
            JUtils.LogError(this, "atTurnStart called before atBattleStartPreDraw");
            battleRNG = new Random(rng.randomLong(), 0);
        }

        final AbstractCard card = CreateRandomCurses.GetRandomCurse(AbstractDungeon.cardRng).makeCopy();
        if (card.canUpgrade() && battleRNG.randomBoolean(0.3f))
        {
            card.upgrade();
        }

        if (player.hand.size() < BaseMod.MAX_HAND_SIZE)
        {
            player.hand.addToTop(card);
            CombatStats.OnCardCreated(card, false);
            flash();
        }
    }
}