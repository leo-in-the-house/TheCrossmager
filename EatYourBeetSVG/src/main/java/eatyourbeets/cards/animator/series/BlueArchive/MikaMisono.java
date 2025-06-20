package eatyourbeets.cards.animator.series.BlueArchive;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.*;

import java.util.ArrayList;

public class MikaMisono extends AnimatorCard {
    public static final EYBCardData DATA = Register(MikaMisono.class)
            .SetPower(1, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public MikaMisono() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_White(1);
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
        public void onPlayCard(AbstractCard card, AbstractMonster m) {
            super.onPlayCard(card, m);

            ArrayList<AbstractCard> cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn;

            if (cardsPlayed.size() % 2 == 0 && cardsPlayed.size() > 0 && card != null) {
                RandomizedList<AbstractMonster> enemies = new RandomizedList<>();

                for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                    if (enemy.hasPower(LockOnPower.POWER_ID)) {
                        enemies.Add(enemy);
                    }
                }

                if (enemies.Size() > 0) {
                    AbstractMonster target = enemies.Retrieve(rng);

                    if (target != null) {
                        GameActions.Bottom.PlayCopy(card, target);
                    }
                }
            }

        }

        @Override
        protected ColoredString GetSecondaryAmount(Color c)
        {
            int cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2;

            return new ColoredString(cardsPlayed, Colors.Lerp(Color.LIGHT_GRAY, Settings.PURPLE_COLOR, cardsPlayed, c.a));
        }


        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}