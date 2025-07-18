package eatyourbeets.cards.animatorClassic.series.GoblinSlayer;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.orbs.animator.Earth;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;

public class DwarfShaman extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(DwarfShaman.class).SetSeriesFromClassPackage().SetAttack(1, CardRarity.COMMON, EYBAttackType.Elemental);

    public DwarfShaman()
    {
        super(DATA);

        Initialize(2, 0, 3);
        SetUpgrade(4, 0, 0);
        SetScaling(1, 0, 1);

        SetEvokeOrbCount(1);
        
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
        GameActions.Bottom.ChannelOrb(new Earth());

        if (WrathStance.IsActive() || IntellectStance.IsActive())
        {
            GameActions.Bottom.Draw(1);
            GameActions.Bottom.UpgradeFromHand(name, 1, false);
        }
    }
}