package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;
import eatyourbeets.cards.animator.series.OwariNoSeraph.Yuuichirou;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Yuuichirou_Asuramaru extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Yuuichirou_Asuramaru.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(Yuuichirou.DATA.Series);

    public Yuuichirou_Asuramaru()
    {
        super(DATA);

        Initialize(0, 0,2);
        SetUpgrade(0, 0,0);

        SetAffinity_Red(1);
        SetAffinity_Black(1);
        SetAffinity_Violet(1);

        SetExhaust(true);
    }

    @Override
    protected void SetUpgrade(int damage, int block) {
        super.SetUpgrade(damage, block);

        SetRetain(true);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
        GameActions.Bottom.StackPower(new DemonFormPower(player, magicNumber));
    }
}