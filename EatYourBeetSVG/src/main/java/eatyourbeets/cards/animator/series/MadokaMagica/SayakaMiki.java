package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.animator.curse.special.Curse_GriefSeed;
import eatyourbeets.cards.animator.special.SayakaMiki_Oktavia;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SayakaMiki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SayakaMiki.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.MadokaMagica_Witch(SayakaMiki_Oktavia.DATA));
                data.AddPreview(new SayakaMiki_Oktavia(), true);
                data.AddPreview(new Curse_GriefSeed(), false);
            });

    public SayakaMiki()
    {
        super(DATA);

        Initialize(0, 3);
        SetUpgrade(0, 3);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_White(1, 0, 1);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetEthereal(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new SayakaMikiPower(p, 1)).ShowEffect(false, false);
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return GameUtilities.GetHealthRecoverAmount(secondaryValue) > 0 && super.CheckSpecialCondition(tryUse);
    }

    public static class SayakaMikiPower extends AnimatorPower
    {
        public SayakaMikiPower(AbstractCreature owner, int amount)
        {
            super(owner, SayakaMiki.DATA);

            Initialize(amount);
        }

        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            GameActions.Bottom.ChannelOrbs(Frost::new, amount);
            GameActions.Bottom.ChannelOrbs(Water::new, amount);
            RemovePower();
        }
    }
}
