package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RagePower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shiroko_Terror extends AnimatorCard {
    public static final EYBCardData DATA = Register(Shiroko_Terror.class)
            .SetSkill(3, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.BlueArchive);

    public Shiroko_Terror() {
        super(DATA);

        Initialize(0, 8, 3, 6);
        SetUpgrade(0, 2, 0, 6);

        SetAffinity_Black(1, 0,1);
        SetAffinity_Teal(1, 0,1);
        SetAffinity_Green(1, 0,1);

        SetEthereal(true);
        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.LoseHP(3, AttackEffects.DARK)
            .AddCallback(damageInfo -> {
                GameActions.Bottom.GainStrength(secondaryValue);
                GameActions.Bottom.StackPower(new RagePower(player, secondaryValue));
            });
    }
}