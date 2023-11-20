package eatyourbeets.cards.animator.series.TouhouProject;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.orbs.animator.Earth;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.ColoredString;
import eatyourbeets.utilities.Colors;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TenshiHinanawi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(TenshiHinanawi.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public TenshiHinanawi()
    {
        super(DATA);

        Initialize(0, 0, 7, 3);
        SetUpgrade(0, 0, -2, 2);

        SetAffinity_Brown(1);
        SetAffinity_Blue(1);

        SetDelayed(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ChannelOrb(new Earth());

        GameActions.Bottom.StackPower(new TenshiHinanawiPower(player, secondaryValue, magicNumber));
    }

    public static class TenshiHinanawiPower extends AnimatorPower implements OnBlockGainedSubscriber
    {
        int counter = 0;
        int counterThreshold;

        public TenshiHinanawiPower(AbstractCreature owner, int amount, int counterThreshold)
        {
            super(owner, TenshiHinanawi.DATA);

            this.counterThreshold = counterThreshold;

            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount, counterThreshold);
        }

        @Override
        public void onInitialApplication() {
            super.onInitialApplication();

            CombatStats.onBlockGained.Subscribe(this);
        }

        @Override
        public void onRemove() {
            super.onRemove();

            CombatStats.onBlockGained.Unsubscribe(this);
        }
        @Override
        protected ColoredString GetSecondaryAmount(Color c)
        {
            return new ColoredString(counter, Colors.Lerp(Color.LIGHT_GRAY, Settings.PURPLE_COLOR, counter, c.a));
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            SetEnabled(false);
            RemovePower();
            flash();
        }

        @Override
        public void OnBlockGained(AbstractCreature creature, int block)
        {
            counter++;

            if (counter == counterThreshold) {

                AbstractOrb earth = null;

                for (AbstractOrb orb : player.orbs) {
                    if (Earth.ORB_ID.equals(orb.ID)) {
                        earth = orb;
                        break;
                    }
                }

                if (earth != null) {
                    for (int i = 0; i < amount; i++) {
                        GameActions.Bottom.EvokeOrb(amount, earth);
                        GameActions.Bottom.ChannelOrb(earth);
                    }
                }

                counter = 0;
            }
        }
    }
}