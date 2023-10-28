package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.relics.DarkstonePeriapt;
import com.megacrit.cardcrawl.relics.DuVuDoll;
import eatyourbeets.cards.animator.curse.special.Curse_GriefSeed;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.relics.animator.SoulGem;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Walpurgisnacht extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(Walpurgisnacht.class)
            .SetPower(3, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.MadokaMagica);

    public Walpurgisnacht()
    {
        super(DATA);

        Initialize(0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(2);
        SetAffinity_Black(2);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new WalpurgisnachtPower(p, 1));

        if (GameUtilities.InEliteOrBossRoom() && CombatStats.TryActivateLimited(cardID)) {
            AbstractRelic relicToGrant = null;

            RandomizedList<AbstractRelic> possibleRelics = new RandomizedList<>();

            if (!player.hasRelic(BlueCandle.ID)) {
                possibleRelics.Add(new BlueCandle());
            }

            if (!player.hasRelic(DarkstonePeriapt.ID)) {
                possibleRelics.Add(new DarkstonePeriapt());
            }

            if (!player.hasRelic(DuVuDoll.ID)) {
                possibleRelics.Add(new DuVuDoll());
            }

            if (!player.hasRelic(SoulGem.ID)) {
                possibleRelics.Add(new SoulGem());
            }

            if (possibleRelics.Size() > 0) {
                relicToGrant = possibleRelics.Retrieve(rng);
            }

            if (relicToGrant != null) {
                AbstractDungeon.getCurrRoom().addRelicToRewards(relicToGrant);
            }
        }
    }

    public static class WalpurgisnachtPower extends AnimatorClickablePower
    {
        public WalpurgisnachtPower(AbstractPlayer owner, int amount)
        {
            super(owner, Walpurgisnacht.DATA, PowerTriggerConditionType.None, 0);

            triggerCondition.SetUses(2, true, true);

            Initialize(amount);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            GameActions.Bottom.MakeCardInHand(AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy());
            GameActions.Bottom.MakeCardInHand(new Curse_GriefSeed());

            flash();
        }
    }
}