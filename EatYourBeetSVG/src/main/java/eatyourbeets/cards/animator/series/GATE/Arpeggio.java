package eatyourbeets.cards.animator.series.GATE;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.ColoredString;
import eatyourbeets.utilities.Colors;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Arpeggio extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Arpeggio.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Arpeggio()
    {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, -2);

        SetAffinity_Blue(1);
        SetAffinity_Yellow(1);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            for (AbstractCard card : player.hand.group) {
                if (!card.uuid.equals(uuid) && card.type != CardType.ATTACK) {
                    return false;
                }
            }

            return player.hand.size() >= magicNumber;
        }

        return false;
    }

    @Override
    protected void OnUpgrade()
    {
        SetDelayed(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new ArpeggioPower(p, 1));
    }

    public static class ArpeggioPower extends AnimatorPower
    {
        protected int charge = 1;

        public ArpeggioPower(AbstractCreature owner, int amount)
        {
            super(owner, Arpeggio.DATA);

            Initialize(amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(0, amount);
        }

        @Override
        protected ColoredString GetSecondaryAmount(Color c)
        {
            return new ColoredString(charge, Colors.Lerp(Color.LIGHT_GRAY, Settings.PURPLE_COLOR, charge, c.a));
        }

        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            if (charge <= 0)
            {
                GameActions.Bottom.GainOrbSlots(amount);
                charge = 1;
                flashWithoutSound();
            }
            else {
                charge--;
            }
        }
    }
}