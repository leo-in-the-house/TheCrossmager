package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;

public class LimeBell extends AnimatorCard
{
    public static final EYBCardData DATA = Register(LimeBell.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.AccelWorld);

    public LimeBell()
    {
        super(DATA);

        Initialize(0, 4, 9);
        SetUpgrade(0, 4, 4);

        SetAffinity_Teal(2);

        SetExhaust(true);
        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block)
        .AddCallback(() ->
        {
            int toConvert = Math.min(magicNumber, player.currentBlock);
            if (toConvert > 0)
            {
                player.loseBlock(toConvert, true);
                GameActions.Bottom.GainTemporaryHP(toConvert);
            }
        });
    }
}