package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.common.Curse_Depression;
import eatyourbeets.cards.animator.special.InverseOrigami;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.animator.SupportDamagePower;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class OrigamiTobiichi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(OrigamiTobiichi.class)
            .SetPower(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.DAL_Inversion(InverseOrigami.DATA));
                data.AddPreview(new InverseOrigami(), false);
                data.AddPreview(new Curse_Depression(), false);
            });

    public OrigamiTobiichi()
    {
        super(DATA);

        Initialize(0, 0, 50, 0);
        SetUpgrade(0, 0, 50);

        SetAffinity_White(1);
        SetAffinity_Blue(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new OrigamiTobiichiPower(p, magicNumber));
    }

    public static class OrigamiTobiichiPower extends AnimatorPower
    {

        public OrigamiTobiichiPower(AbstractPlayer owner, int amount)
        {
            super(owner, OrigamiTobiichi.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void stackPower(int stackAmount)
        {
            super.stackPower(stackAmount);

            updateDescription();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            if (isPlayer)
            {
                flash();

                GameActions.Bottom.StackPower(new SupportDamagePower(player, (amount / 100) * player.currentBlock));
            }
        }

    }
}