package eatyourbeets.cards.animatorClassic.series.OwariNoSeraph;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class Shinoa extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Shinoa.class).SetSeriesFromClassPackage().SetSkill(1, CardRarity.COMMON, EYBCardTarget.ALL);

    public Shinoa()
    {
        super(DATA);

        Initialize(0, 6, 1);
        SetUpgrade(0, 3, 0);

        
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Bottom.ApplyWeak(TargetHelper.Enemies(), magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ApplyVulnerable(TargetHelper.Enemies(), magicNumber);

        if (info.IsSynergizing)
        {
            GameActions.Bottom.ApplyWeak(TargetHelper.Enemies(), magicNumber);
        }
    }
}