package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.ElectroPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.replacement.TemporaryElectroPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class NarberalGamma extends AnimatorCard
{
    public static final EYBCardData DATA = Register(NarberalGamma.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public NarberalGamma()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Yellow(1);
        SetAffinity_Brown(1);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ChannelOrbs(Lightning::new, magicNumber);

        if (!p.hasPower(ElectroPower.POWER_ID))
        {
            GameActions.Bottom.ApplyPower(p, p, new TemporaryElectroPower(p));
        }
    }
}