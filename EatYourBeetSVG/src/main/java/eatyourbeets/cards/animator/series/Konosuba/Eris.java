package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Eris extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Eris.class)
            .SetSkill(4, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Eris()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_White(2);

        SetExhaust(true);
        SetRetain(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(new RainbowCardEffect());

        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.GainEnergy(magicNumber);
            GameActions.Bottom.Draw(1);
        }

        GameActions.Bottom.StackPower(new ErisPower(p, 1));
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        AbstractCard last = GameUtilities.GetLastCardPlayed(this, true);

        return GameUtilities.IsSeries(last, CardSeries.Konosuba);
    }

    public static class ErisPower extends AnimatorPower
    {
        public ErisPower(AbstractCreature owner, int amount)
        {
            super(owner, Eris.DATA);

            Initialize(amount, PowerType.BUFF, true);
        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(0, amount);
        }

        @Override
        public float atDamageFinalReceive(float damage, DamageInfo.DamageType type)
        {
            return 0;
        }

        @Override
        public void atStartOfTurnPostDraw()
        {
            super.atStartOfTurnPostDraw();

            ReducePower(1);
        }
    }
}