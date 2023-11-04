package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Cocytus extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Cocytus.class)
            .SetAttack(3, CardRarity.COMMON)
            .SetSeriesFromClassPackage();

    public Cocytus()
    {
        super(DATA);

        Initialize(8, 8, 3, 0);
        SetUpgrade(6, 6, 0, 0);

        SetAffinity_Blue(2, 0, 0);
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        super.OnDrag(m);

        if (m != null)
        {
            GameUtilities.GetIntent(m).AddFreezing();
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ApplyFreezing(p, m, magicNumber);
    }
}