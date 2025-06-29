package eatyourbeets.cards.animatorClassic.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.GameActions;

public class LimeBell extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(LimeBell.class).SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None).SetColor(CardColor.COLORLESS).SetSeries(CardSeries.AccelWorld);

    public LimeBell()
    {
        super(DATA);

        Initialize(0, 8, 2);
        SetUpgrade(0, 4, 0);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.Reload(name, cards -> GameActions.Bottom.GainTemporaryHP(cards.size() * magicNumber));
    }
}