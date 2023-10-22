package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Tetora extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Tetora.class)
            .SetPower(0, CardRarity.UNCOMMON)
            
            .SetSeriesFromClassPackage();

    public Tetora()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_White(1);
        SetAffinity_Yellow(1);

        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new TetoraPower(p, magicNumber));
    }

    public static class TetoraPower extends AnimatorPower
    {

        public TetoraPower(AbstractPlayer owner, int amount)
        {
            super(owner, Tetora.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            for (int i=0; i<amount; i++) {
                if (rng.randomBoolean()) {
                    GameActions.Bottom.GainWhite(1);
                }
                else {
                    GameActions.Bottom.GainYellow(1);
                }
            }
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}