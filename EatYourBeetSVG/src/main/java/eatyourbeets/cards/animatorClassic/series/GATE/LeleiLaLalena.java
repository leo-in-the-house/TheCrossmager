package eatyourbeets.cards.animatorClassic.series.GATE;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class LeleiLaLalena extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(LeleiLaLalena.class).SetSeriesFromClassPackage().SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None);

    public LeleiLaLalena()
    {
        super(DATA);

        Initialize(0, 0, 1);

        SetEvokeOrbCount(1);

        
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        if (m != null && HasSynergy())
        {
            GameUtilities.GetIntent(m).AddWeak();
        }
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        target = HasSynergy() ? CardTarget.SELF_AND_ENEMY : CardTarget.SELF;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        if (info.IsSynergizing)
        {
            if (m == null)
            {
                m = GameUtilities.GetRandomEnemy(true);
            }

            GameActions.Bottom.ApplyWeak(p, m, 1);
        }
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DiscardFromHand(name, 1, !upgraded)
        .ShowEffect(!upgraded, !upgraded)
        .SetOptions(false, false, false)
        .AddCallback(() -> GameActions.Bottom.ChannelOrbs(Frost::new, magicNumber));
    }
}