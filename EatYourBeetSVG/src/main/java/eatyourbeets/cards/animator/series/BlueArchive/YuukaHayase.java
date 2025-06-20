package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GenericCondition;
import eatyourbeets.utilities.WeightedList;

public class YuukaHayase extends AnimatorCard {
    public static final EYBCardData DATA = Register(YuukaHayase.class)
            .SetSkill(3, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public YuukaHayase() {
        super(DATA);

        Initialize(2, 0, 3);
        SetUpgrade(2, 0, 0);

        SetAffinity_Yellow(2, 0, 1);
        SetAffinity_Pink(2, 0, 1);

        SetExhaust(true);

        SetAffinityRequirement(Affinity.Yellow, 4);
        SetAffinityRequirement(Affinity.Pink, 4);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        WeightedList<AbstractCard> options = GetLockOnCards();

        if (options.Size() > 0) {
            GameActions.Bottom.MakeCardInHand(options.Retrieve(rng).makeCopy())
                .AddCallback(card -> {
                    CostModifiers.For(card).Add(-99);
                });
        }

        if (CheckSpecialCondition(false) && CombatStats.TryActivateLimited(cardID))
        {
            int goldToObtain = 0;

            for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                if (enemy.hasPower(LockOnPower.POWER_ID)) {
                    goldToObtain += 5 * GameUtilities.GetPowerAmount(enemy, LockOnPower.POWER_ID);
                }
            }

            if (goldToObtain > 0) {
                AbstractDungeon.effectList.add(new RainingGoldEffect(goldToObtain));
                GameActions.Bottom.GainGold(goldToObtain);
            }
        }
    }

    private WeightedList<AbstractCard> GetLockOnCards()
    {

        //Change this if you change Lock-On's shorthand
        String lockOnString = "[~Lock-On]";

        WeightedList<AbstractCard> possibleCards = GameUtilities.GetCardsInCombatWeighted(GenericCondition.FromT1(c -> GameUtilities.DescriptionContainsIcon(c, lockOnString)));

        return possibleCards;
    }
}