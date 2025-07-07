package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class MikaMisono extends AnimatorCard {
    public static final EYBCardData DATA = Register(MikaMisono.class)
            .SetPower(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public MikaMisono() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_White(1);
        SetAffinity_Violet(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new MikaMisonoPower(p, 1));
    }

    public static class MikaMisonoPower extends AnimatorPower {
        public MikaMisonoPower(AbstractCreature owner, int amount) {
            super(owner, MikaMisono.DATA);
            Initialize(amount);
        }


        @Override
        public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
            super.atEndOfTurnPreEndTurnCards(isPlayer);

            if (isPlayer) {
                for (AbstractCard card : player.hand.group) {
                    if (card.type == CardType.ATTACK) {
                        RandomizedList<AbstractMonster> enemies = new RandomizedList<>();

                        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                            if (enemy.hasPower(LockOnPower.POWER_ID)) {
                                enemies.Add(enemy);
                            }
                        }

                        if (enemies.Size() > 0) {
                            AbstractMonster target = enemies.Retrieve(rng);

                            if (target != null) {
                                for (int i=0; i<amount; i++) {
                                    GameActions.Bottom.PlayCard(card.makeCopy(), target);
                                }
                            }
                        }
                    }
                }
            }

        }


        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}