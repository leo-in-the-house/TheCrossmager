package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.JuggernautPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class SirTouchMe extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(SirTouchMe.class)
            .SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Overlord);

    public SirTouchMe()
    {
        super(DATA);

        Initialize(0, 3, 4);
        SetUpgrade(0, 0, 2);
        
        SetAffinity_Red(2, 0, 1);
        SetAffinity_White(2, 0, 1);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        int juggernautAmount = 0;

        for (AbstractPower debuff : GameUtilities.GetCommonDebuffs(TargetHelper.Enemies())) {
            juggernautAmount += debuff.amount;
        }

        if (juggernautAmount > 0) {
            GameActions.Bottom.StackPower(new JuggernautPower(p, juggernautAmount));
        }
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (startOfBattle)
        {
            GameEffects.List.ShowCopy(this);
            GameActions.Bottom.GainPlatedArmor(secondaryValue);
        }
    }
}