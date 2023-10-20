package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Konayuki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Konayuki.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage();

    public Konayuki()
    {
        super(DATA);

        Initialize(0, 6, 3);
        SetUpgrade(0, 5, 2);

        SetAffinity_Red(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        CombatStats.Affinities.AddAffinitySealUses(1);
        GameActions.Bottom.GainRed(magicNumber);
    }
}